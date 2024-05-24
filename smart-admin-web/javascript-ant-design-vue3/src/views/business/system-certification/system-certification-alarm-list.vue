<!--
  * 体系认证表
  *
  * @Author:    cbh
  * @Date:      2024-04-25 14:53:11
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="项目编号" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.projectNo" placeholder="项目编号" />
            </a-form-item>
            <a-form-item label="项目类型" class="smart-query-form-item">
                <SmartEnumSelect width="150px" v-model:value="queryForm.projectType" enumName="PROJECT_TYPE_SYSTEM_ENUM"
                    placeholder="项目类型" />
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
            <a-form-item label="认证机构" class="smart-query-form-item">
                <ThirdPartySelect width="150px" v-model:value="queryForm.thirdPartyId" placeholder="请选择认证机构"
                    type="THIRD_2" />
            </a-form-item>
            <a-form-item label="合同号" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.contractNo" placeholder="合同号" />
            </a-form-item>
            <a-form-item label="合同日" class="smart-query-form-item">
                <a-range-picker v-model:value="queryForm.contractDate" :presets="defaultTimeRanges" style="width: 250px"
                    @change="onChangeContractDate" />
            </a-form-item>
            <a-form-item label="客户预期日期" class="smart-query-form-item">
                <a-range-picker v-model:value="queryForm.expectedDate" :presets="defaultTimeRanges" style="width: 250px"
                    @change="onChangeExpectedDate" />
            </a-form-item>
            <a-form-item label="创建人" class="smart-query-form-item">
                <EmployeeSelect ref="employeeSelect" placeholder="请选择创建人" width="200px"
                    v-model:value="queryForm.createUserId" :leaveFlag="false" />
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
        <!---------- 表格操作行 begin ----------->
        <a-row class="smart-table-btn-block">
            <div class="smart-table-operate-block">
                <a-button @click="showFormAddTo(PROJECT_TYPE_SYSTEM_ENUM.SUPERVISION.value)" type="primary" size="small"
                    :disabled="toSupervision">
                    <template #icon>
                        <ArrowRightOutlined />
                    </template>
                    转监督
                </a-button>
                <a-button @click="showFormAddTo(PROJECT_TYPE_SYSTEM_ENUM.RE_CERTIFICATION.value)" type="primary"
                    size="small" :disabled="reCertification">
                    <template #icon>
                        <RollbackOutlined />
                    </template>
                    再认证
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false"
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio', selections: selectedRowsList }">
            <template #bodyCell="{ text, record, column }">

                <template v-if="column.dataIndex === 'projectNo'">
                    <a @click="detail(record.id)">{{ record.projectNo }}</a>
                </template>
                <template v-if="column.dataIndex === 'projectType'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_SYSTEM_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'sourceType'">
                    <span>{{ $smartEnumPlugin.getDescByValue('SOURCE_TYPE_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'status'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-dropdown v-if="record.systemCertificationNodeList.length > 0">
                            <a class="ant-dropdown-link" @click.prevent>
                                节点操作
                            </a>
                            <template #overlay>
                                <a-menu @click="handleMenuClick($event, record)">
                                    <a-menu-item v-for="node in record.systemCertificationNodeList" :key="node">
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

        <SystemCertificationForm ref="formRef" @reloadList="queryData" />
        <SystemCertificationFormAdd ref="formAddRef" @reloadList="queryData" />
        <FirstPaymentForm ref="firstPaymentRef" @reloadList="queryData" />
        <SubmitDataForm ref="submitDataFormRef" @reloadList="queryData" />
        <ReviewPlanForm ref="reviewPlanFormRef" @reloadList="queryData" />
        <SubmitPaperForm ref="submitPaperFormRef" @reloadList="queryData" />
        <AssessmentDateForm ref="assessmentDateFormRef" @reloadList="queryData" />
        <RectificationDateForm ref="rectificationDateFormRef" @reloadList="queryData" />
        <SystemCertificateForm ref="systemCertificateFormRef" @reloadList="queryData" />
        <FinalPaymentForm ref="finalPaymentFormRef" @reloadList="queryData" />
        <InvoiceForm ref="invoiceFormRef" @reloadList="queryData" />
        <MailForm ref="mailFormRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { PROJECT_TYPE_SYSTEM_ENUM } from '/@/constants/business/project/system-certification-const';
import { smartSentry } from '/@/lib/smart-sentry';
import { useRouter, useRoute } from 'vue-router';
import TableOperator from '/@/components/support/table-operator/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import SystemCertificationForm from './system-certification-form.vue';
import SystemCertificationFormAdd from './system-certification-form-add.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';
import FirstPaymentForm from '../common-nodes/first-payment/first-payment-form.vue';
import SubmitDataForm from './nodes/submit-data/submit-data-form.vue';
import NODE_CONST from '/@/constants/business/project/node-const';
import ReviewPlanForm from './nodes/review-plan/review-plan-form.vue';
import SubmitPaperForm from './nodes/submit-paper/submit-paper-form.vue';
import AssessmentDateForm from './nodes/assessment-date/assessment-date-form.vue';
import RectificationDateForm from './nodes/rectification-date/rectification-date-form.vue';
import SystemCertificateForm from './nodes/system-certificate/system-certificate-form.vue';
import FinalPaymentForm from '../common-nodes/final-payment/final-payment-form.vue';
import InvoiceForm from '../common-nodes/invoice/invoice-form.vue';
import MailForm from '../common-nodes/mail/mail-form.vue';

// ---------------------------- 表格列 ----------------------------

const columns = ref([

    {
        title: '项目编号',
        dataIndex: 'projectNo',
        ellipsis: false,
        width: 150,
    },
    {
        title: '项目类型',
        dataIndex: 'projectType',
        width: 80,
    },
    {
        title: '客户',
        dataIndex: 'customerName',
        ellipsis: true,
        width: 150,
    },
    {
        title: '认证机构',
        dataIndex: 'thirdPartyName',
        width: 120,
    },
    // {
    //     title: '合同号',
    //     dataIndex: 'contractNo',
    //     ellipsis: true,
    // },
    // {
    //     title: '合同日',
    //     dataIndex: 'contractDate',
    //     ellipsis: true,
    // },
    {
        title: '合同金额',
        dataIndex: 'contractAmount',
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
    //     title: '资料上报日期',
    //     dataIndex: 'dataReportDate',
    //     ellipsis: true,
    // },
    {
        title: '审核老师',
        dataIndex: 'auditTeacher',
        width: 80,
    },
    {
        title: '审核日期',
        dataIndex: 'auditDate',
        ellipsis: true,
    },
    {
        title: '咨询老师',
        dataIndex: 'consultationTeacher',
        width: 80,
    },
    // {
    //     title: '交卷日期',
    //     dataIndex: 'submissionDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '评定日期',
    //     dataIndex: 'assessmentDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '整改日期',
    //     dataIndex: 'rectificationDate',
    //     ellipsis: true,
    // },
    {
        title: '证书发送日期',
        dataIndex: 'certificateSendDate',
        ellipsis: true,
    },
    // {
    //     title: '证书有效期截止日期',
    //     dataIndex: 'certificateExpiryDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '尾款金额',
    //     dataIndex: 'finalPaymentAmount',
    //     ellipsis: true,
    // },
    // {
    //     title: '尾款收款日期',
    //     dataIndex: 'finalPaymentDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '开票日期',
    //     dataIndex: 'invoiceDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '发票金额',
    //     dataIndex: 'invoiceAmount',
    //     ellipsis: true,
    // },
    {
        title: '邮寄日期',
        dataIndex: 'mailingDate',
        ellipsis: true,
    },
    {
        title: '状态',
        dataIndex: 'status',
        width: 70,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        ellipsis: true,
        width: 170,
    },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 120,
    },
]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    alarmType: undefined, //预警类型
    projectNo: undefined, //项目编号
    projectType: undefined, //项目类型
    category: undefined, //项目分类
    customerId: undefined, //客户ID
    sourceType: undefined, //来源分类
    sourceId: undefined, //来源ID
    thirdPartyId: undefined, //认证机构ID
    contractNo: undefined, //合同号
    contractDate: [], //合同日
    contractDateBegin: undefined, //合同日 开始
    contractDateEnd: undefined, //合同日 结束
    expectedDate: [], //客户预期日期
    expectedDateBegin: undefined, //客户预期日期 开始
    expectedDateEnd: undefined, //客户预期日期 结束
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
    queryData();
}

// 查询数据
async function queryData() {
    tableLoading.value = true;
    try {
        let queryResult = await systemCertificationApi.queryAlarmPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

function onChangeContractDate(dates, dateStrings) {
    queryForm.contractDateBegin = dateStrings[0];
    queryForm.contractDateEnd = dateStrings[1];
}

function onChangeExpectedDate(dates, dateStrings) {
    queryForm.expectedDateBegin = dateStrings[0];
    queryForm.expectedDateEnd = dateStrings[1];
}

function onChangeCreateTime(dates, dateStrings) {
    queryForm.createTimeBegin = dateStrings[0];
    queryForm.createTimeEnd = dateStrings[1];
}

let route = useRoute();

onMounted(() => {
    // 获取最后一个"/"之后的值
    const lastSlashIndex = route.path.lastIndexOf('/');
    if (lastSlashIndex !== -1) {
        const type = route.path.slice(lastSlashIndex + 1);
        queryForm.alarmType = type;
        queryData();
    }

});

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();
const formAddRef = ref();
const firstPaymentRef = ref();
const submitDataFormRef = ref();
const reviewPlanFormRef = ref();
const submitPaperFormRef = ref();
const assessmentDateFormRef = ref();
const rectificationDateFormRef = ref();
const systemCertificateFormRef = ref();
const finalPaymentFormRef = ref();
const invoiceFormRef = ref();
const mailFormRef = ref();

function showFormAdd() {
    formAddRef.value.show();
}

function showFormAddTo(projectType) {
    let rowData = selectedRowsList.value[0];
    rowData.projectType = projectType;
    rowData.id = undefined;
    formAddRef.value.show(rowData);
}

function showForm() {
    if (selectedRowKeyList.value.length === 1) {
        formRef.value.show(selectedRowKeyList.value[0]);
    } else {
        message.error('请选择一条数据');
    }
}

const handleMenuClick = (e, param) => {
    if (e.key.nodeId === NODE_CONST.first_payment) {
        firstPaymentRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.submit_data) {
        submitDataFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.review_plan) {
        reviewPlanFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.submit_paper) {
        submitPaperFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.assessment_date) {
        assessmentDateFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.rectification_date) {
        rectificationDateFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.certificate) {
        systemCertificateFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.final_payment) {
        finalPaymentFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.invoice) {
        invoiceFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.mail) {
        mailFormRef.value.show(param, e.key.id);
    }
};

// 选择表格行
const selectedRowKeyList = ref([]);
const selectedRowsList = ref([]);
const toSupervision = ref(true);
const reCertification = ref(true);

function onSelectChange(selectedRowKeys, selections) {
    selectedRowKeyList.value = selectedRowKeys;
    selectedRowsList.value = selections;
    if (selectedRowsList.value.length == 1) {
        if (selectedRowsList.value[0].projectType == PROJECT_TYPE_SYSTEM_ENUM.INITIALIZATION.value) {
            toSupervision.value = false;
        }
        if (selectedRowsList.value[0].projectType == PROJECT_TYPE_SYSTEM_ENUM.SUPERVISION.value) {
            reCertification.value = false;
        }
    } else {
        toSupervision.value = true;
        reCertification.value = true;
    }

}

let router = useRouter();

function detail(id) {
    router.push({ path: '/system-certification/detail', query: { id: id } });
}

// --------------------------- 导出 ---------------------------
async function exportExcel() {
    await systemCertificationApi.exportExcel(queryForm);
}

</script>
