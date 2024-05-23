<!--
  * 项目表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:54:05
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="项目编号" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.projectNo" placeholder="项目编号" />
            </a-form-item>
            <a-form-item label="客户" class="smart-query-form-item">
                <CustomerSelect width="150px" v-model:value="queryForm.customerId" placeholder="请选择客户" />
            </a-form-item>
            <a-form-item label="来源分类" class="smart-query-form-item">
                <SmartEnumSelect width="150px" v-model:value="queryForm.sourceType" enumName="SOURCE_TYPE_ENUM"
                    placeholder="来源分类" />
            </a-form-item>
            <a-form-item label="来源" class="smart-query-form-item">

                <template v-if="queryForm.sourceType === 1">
                    <ThirdPartySelect width="150px" v-model:value="queryForm.sourceId" placeholder="请选择咨询机构"
                        type="THIRD_3" />
                </template>
                <template v-else-if="queryForm.sourceType === 2">
                    <EmployeeSelect width="150px" v-model:value="queryForm.sourceId" placeholder="请选择内部员工" />
                </template>
                <template v-else="queryForm.sourceType === 1">
                    <ThirdPartySelect width="150px" v-model:value="queryForm.sourceId" placeholder="请选择咨询机构"
                        type="THIRD_3" />
                </template>
            </a-form-item>
            <a-form-item label="创建人" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.createUserId" placeholder="创建人" />
            </a-form-item>
            <a-form-item label="创建时间" class="smart-query-form-item">
                <a-range-picker v-model:value="queryForm.createTime" :presets="defaultTimeRanges" style="width: 250px"
                    @change="onChangeCreateTime" />
            </a-form-item>
            <a-form-item class="smart-query-form-item">
                <a-button type="primary" @click="queryData">
                    <template #icon>
                        <SearchOutlined />
                    </template>
                    查询
                </a-button>
                <a-button @click="resetQuery" class="smart-margin-left10">
                    <template #icon>
                        <ReloadOutlined />
                    </template>
                    重置
                </a-button>
            </a-form-item>
        </a-row>
    </a-form>
    <!---------- 查询表单form end ----------->

    <a-card size="small" :bordered="false" :hoverable="true">


        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false"
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio' }">
            <template #bodyCell="{ text, record, column }">

                <template v-if="column.dataIndex === 'projectNo'">
                    <a @click="detail(record.id)">{{ record.projectNo }}</a>
                </template>
                <template v-if="column.dataIndex === 'projectType'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_PRODUCT_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'category'">
                    <span>{{ $smartEnumPlugin.getDescByValue(enumName, text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'sourceType'">
                    <span>{{ $smartEnumPlugin.getDescByValue('SOURCE_TYPE_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'status'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-dropdown>
                            <a class="ant-dropdown-link" @click.prevent>
                                节点操作
                            </a>
                            <template #overlay>
                                <a-menu @click="handleMenuClick($event, record)">
                                    <a-menu-item v-if="record.projectType != 31">
                                        实验室任务
                                    </a-menu-item>
                                    <a-menu-divider v-if="record.projectType != 31" />
                                    <a-menu-item v-for="node in record.projectNodeList" :key="node">
                                        {{ node.nodeName }}
                                    </a-menu-item>
                                </a-menu>
                            </template>
                        </a-dropdown>
                    </div>
                </template>
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <div class="smart-query-table-page">
            <a-pagination showSizeChanger showQuickJumper show-less-items :pageSizeOptions="PAGE_SIZE_OPTIONS"
                :defaultPageSize="queryForm.pageSize" v-model:current="queryForm.pageNum"
                v-model:pageSize="queryForm.pageSize" :total="total" @change="queryData" @showSizeChange="queryData"
                :show-total="(total) => `共${total}条`" />
        </div>

        <FirstPaymentForm ref="firstPaymentRef" @reloadList="queryData" />
        <FinalPaymentForm ref="finalPaymentFormRef" @reloadList="queryData" />
        <InvoiceForm ref="invoiceFormRef" @reloadList="queryData" />
        <FactoryAuditTaskForm ref="factoryAuditTaskFormRef" @reloadList="queryData" />
        <FactoryAuditForm ref="factoryAuditFormRef" @reloadList="queryData" />
        <CorrectionForm ref="correctionFormRef" @reloadList="queryData" />
        <SubmitCertificationFeeForm ref="submitCertificationFeeFormRef" @reloadList="queryData" />
        <ArchiveForm ref="archiveFormRef" @reloadList="queryData" />
        <ProjectMailForm ref="projectMailFormRef" @reloadList="queryData" />

    </a-card>

</template>
<script setup>
import { reactive, ref, onMounted, computed } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectApi } from '/@/api/business/project/project-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import NODE_CONST from '/@/constants/business/project/node-const';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';
import FirstPaymentForm from '../common-nodes/first-payment/first-payment-form.vue';
import FinalPaymentForm from '../common-nodes/final-payment/final-payment-form.vue';
import InvoiceForm from '../common-nodes/invoice/invoice-form.vue';
import FactoryAuditTaskForm from '../common-nodes/factory-audit-task/factory-audit-task-form.vue';
import FactoryAuditForm from '../common-nodes/factory-audit/factory-audit-form.vue';
import CorrectionForm from '../common-nodes/correction/correction-form.vue';
import SubmitCertificationFeeForm from '../common-nodes/submit-certification-fee/submit-certification-fee-form.vue';
import ArchiveForm from '../common-nodes/archive/archive-form.vue';
import ProjectMailForm from '../common-nodes/project-mail/project-mail-form.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([

    {
        title: '项目编号',
        dataIndex: 'projectNo',
        width: 150,
    },
    {
        title: '项目类型',
        dataIndex: 'projectType',
        ellipsis: true,
    },
    {
        title: '项目分类',
        dataIndex: 'category',
        ellipsis: true,
    },
    {
        title: '客户',
        dataIndex: 'customerName',
        ellipsis: true,
    },
    {
        title: '来源分类',
        dataIndex: 'sourceType',
        ellipsis: true,
    },
    {
        title: '来源',
        dataIndex: 'sourceName',
        ellipsis: true,
    },
    {
        title: '合同号',
        dataIndex: 'contractNo',
        ellipsis: true,
    },
    {
        title: '合同日',
        dataIndex: 'contractDate',
        ellipsis: true,
    },
    {
        title: '合同金额',
        dataIndex: 'contractAmount',
        ellipsis: true,
    },
    {
        title: '客户预期日期',
        dataIndex: 'expectedDate',
        ellipsis: true,
    },
    // {
    //     title: '首款金额',
    //     dataIndex: 'firstPaymentAmount',
    //     ellipsis: true,
    // },
    // {
    //     title: '首款收款日期',
    //     dataIndex: 'firstPaymentDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '审核任务时间',
    //     dataIndex: 'auditTaskDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '审核老师',
    //     dataIndex: 'auditTeacher',
    //     ellipsis: true,
    // },
    // {
    //     title: '咨询老师',
    //     dataIndex: 'consultationTeacher',
    //     ellipsis: true,
    // },
    // {
    //     title: '审核日期',
    //     dataIndex: 'auditDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '整改内容',
    //     dataIndex: 'rectificationContent',
    //     ellipsis: true,
    // },
    // {
    //     title: '整改日期',
    //     dataIndex: 'rectificationDate',
    //     ellipsis: true,
    // },
    {
        title: '状态',
        dataIndex: 'status',
        ellipsis: true,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 170,
    },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 90,
    },
]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    projectNo: undefined, //项目编号
    toDoType: undefined, //待办类型
    category: undefined, //项目分类
    customerId: undefined, //客户ID
    sourceType: undefined, //来源分类
    sourceId: undefined, //来源ID
    createUserId: undefined, //创建人
    createTime: [], //创建时间
    createTimeBegin: undefined, //创建时间 开始
    createTimeEnd: undefined, //创建时间 结束
    pageNum: 1,
    pageSize: 10,
};
// 查询表单form
const queryForm = reactive({ ...queryFormState });
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);

// 重置查询条件
function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryForm.projectType = projectType;
    queryData();
}

// 查询数据
async function queryData() {
    tableLoading.value = true;
    try {
        let queryResult = await projectApi.queryToDoPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

function onChangeCreateTime(dates, dateStrings) {
    queryForm.createTimeBegin = dateStrings[0];
    queryForm.createTimeEnd = dateStrings[1];
}

let route = useRoute();
const projectType = ref(null); // 定义单独的 projectType 变量

onMounted(() => {
    // 获取最后一个"/"之后的值
    const lastSlashIndex = route.path.lastIndexOf('/');
    if (lastSlashIndex !== -1) {
        const type = route.path.slice(lastSlashIndex + 1);
        queryForm.toDoType = type;
        queryData();
    }

});

const enumName = computed(() => {
    return projectType.value == 21 ? 'LAB_CATEGORY_ENUM' : 'PROJECT_CATEGORY_ENUM';
});

// ---------------------------- 添加/修改 ----------------------------

const firstPaymentRef = ref();
const finalPaymentFormRef = ref();
const invoiceFormRef = ref();
const factoryAuditTaskFormRef = ref();
const factoryAuditFormRef = ref();
const correctionFormRef = ref();
const submitCertificationFeeFormRef = ref();
const archiveFormRef = ref();
const projectMailFormRef = ref();


const handleMenuClick = (e, param) => {

    console.log(e.key);
    if (typeof e.key.nodeId === 'undefined') {
        router.push({
            path: '/project/lab-list', query: {
                projectId: param.id,
                projectType: param.projectType
            }
        });
    } else if (e.key.nodeId === NODE_CONST.first_payment) {
        firstPaymentRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.final_payment) {
        finalPaymentFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.invoice) {
        invoiceFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.factory_audit_task) {
        factoryAuditTaskFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.factory_audit) {
        factoryAuditFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.non_conformity_correction) {
        correctionFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.submit_certification_fee) {
        submitCertificationFeeFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.archive) {
        archiveFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.mail) {
        projectMailFormRef.value.show(param, e.key.id);
    }

};

let router = useRouter();

function detail(id) {
    router.push({ path: '/project/product-certification-detail', query: { id: id } });
}
</script>
