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
            <a-form-item label="类别" class="smart-query-form-item">
                <DictSelect width="150px" v-model:value="queryForm.category" keyCode="SYSTEM_CATEGORY"
                    placeholder="类别" />
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
            <a-form-item label="状态" class="smart-query-form-item">
                <SmartEnumSelect width="150px" v-model:value="queryForm.status" enumName="PROJECT_STATUS_ENUM"
                    placeholder="状态" />
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
            <!-- <a-form-item label="创建人" class="smart-query-form-item">
                <EmployeeSelect ref="employeeSelect" placeholder="请选择创建人" width="200px"
                    v-model:value="queryForm.createUserId" :leaveFlag="false" />
            </a-form-item> -->
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
                <a-button @click="showFormAdd" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
                <a-button @click="showForm" type="primary" size="small" :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <EditOutlined />
                    </template>
                    编辑
                </a-button>
                <a-button @click="showFormAddTo(PROJECT_TYPE_SYSTEM_ENUM.SUPERVISION1.value)" type="primary"
                    size="small">
                    <template #icon>
                        <ArrowRightOutlined />
                    </template>
                    转监督
                </a-button>
                <a-button @click="showFormAddTo(PROJECT_TYPE_SYSTEM_ENUM.RE_CERTIFICATION.value)" type="primary"
                    size="small">
                    <template #icon>
                        <RollbackOutlined />
                    </template>
                    再认证
                </a-button>
                <a-button @click="exportExcel()" type="primary" size="small">
                    <template #icon>
                        <FileExcelOutlined />
                    </template>
                    导出数据
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" @resizeColumn="handleResizeColumn" rowKey="id"
            bordered :loading="tableLoading" :pagination="false"
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
                        <!-- <a-button @click="showForm(record)" type="link">编辑</a-button> -->
                        <a-button @click="onDelete(record)" danger type="link">删除</a-button>
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
        <PreDataTransferForm ref="preDataTransferFormRef" @reloadList="queryData" />
        <SystemFileTransferForm ref="systemFileTransferFormRef" @reloadList="queryData" />
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
import { useRouter } from 'vue-router';
import TableOperator from '/@/components/support/table-operator/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import SystemCertificationForm from './system-certification-form.vue';
import SystemCertificationFormAdd from './system-certification-form-add.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';
import DictSelect from '/@/components/support/dict-select/index.vue';
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
import PreDataTransferForm from './nodes/pre-data-transfer/pre-data-transfer-form.vue';
import SystemFileTransferForm from './nodes/system-file-transfer/system-file-transfer-form.vue';

// ---------------------------- 表格列 ----------------------------

const columns = ref([

    {
        title: '项目编号',
        dataIndex: 'projectNo', fixed: 'left',
        width: 150,
    },
    {
        title: '项目类型',
        dataIndex: 'projectType',
        width: 140,
    },
    {
        title: '类别',
        dataIndex: 'category',
        width: 110,
    },
    {
        title: '客户',
        dataIndex: 'customerName',
        width: 120,
    },
    {
        title: '认证机构',
        dataIndex: 'thirdPartyName',
        width: 150,
    },
    // {
    //     title: '合同号',
    //     dataIndex: 'contractNo',
    //     width: 120,
    // },
    // {
    //     title: '合同日',
    //     dataIndex: 'contractDate',
    //     width: 120,
    // },
    {
        title: '合同金额',
        dataIndex: 'contractAmount',
        width: 120,
    },
    // {
    //     title: '首款金额',
    //     dataIndex: 'firstPaymentAmount',
    //     width: 120,
    // },
    // {
    //     title: '首款收款日期',
    //     dataIndex: 'firstPaymentDate',
    //     width: 120,
    // },
    // {
    //     title: '资料上报日期',
    //     dataIndex: 'dataReportDate',
    //     width: 120,
    // },
    {
        title: '审核老师',
        dataIndex: 'auditTeacher',
        width: 120,
    },
    {
        title: '审核日期',
        dataIndex: 'auditDate',
        width: 150,
    },
    {
        title: '咨询老师',
        dataIndex: 'consultationTeacher',
        width: 120,
    },
    // {
    //     title: '交卷日期',
    //     dataIndex: 'submissionDate',
    //     width: 120,
    // },
    // {
    //     title: '评定日期',
    //     dataIndex: 'assessmentDate',
    //     width: 120,
    // },
    // {
    //     title: '整改日期',
    //     dataIndex: 'rectificationDate',
    //     width: 120,
    // },
    {
        title: '证书发送日期',
        dataIndex: 'certificateSendDate',
        width: 160,
    },
    // {
    //     title: '证书有效期截止日期',
    //     dataIndex: 'certificateExpiryDate',
    //     width: 120,
    // },
    // {
    //     title: '尾款金额',
    //     dataIndex: 'finalPaymentAmount',
    //     width: 120,
    // },
    // {
    //     title: '尾款收款日期',
    //     dataIndex: 'finalPaymentDate',
    //     width: 120,
    // },
    // {
    //     title: '开票日期',
    //     dataIndex: 'invoiceDate',
    //     width: 120,
    // },
    // {
    //     title: '发票金额',
    //     dataIndex: 'invoiceAmount',
    //     width: 120,
    // },
    {
        title: '邮寄日期',
        dataIndex: 'mailingDate',
        width: 150,
    },
    {
        title: '状态',
        dataIndex: 'status',
        width: 90,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 120,
    },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 120,
    },
].map(column => ({ ...column, resizable: true })));

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    projectNo: undefined, //项目编号
    projectType: undefined, //项目类型
    category: undefined, //项目分类
    customerId: undefined, //客户ID
    sourceType: undefined, //来源分类
    sourceId: undefined, //来源ID
    status: undefined, //状态
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
        let queryResult = await systemCertificationApi.queryPage(queryForm);
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


onMounted(queryData);

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
const preDataTransferFormRef = ref();
const systemFileTransferFormRef = ref();

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
    } else if (e.key.nodeId === NODE_CONST.pre_data_transfer) {
        preDataTransferFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.system_file_transfer) {
        systemFileTransferFormRef.value.show(param, e.key.id);
    }
};

// ---------------------------- 单个删除 ----------------------------
//确认删除
function onDelete(data) {
    Modal.confirm({
        title: '提示',
        content: '确定要删除选吗?',
        okText: '删除',
        okType: 'danger',
        onOk() {
            requestDelete(data);
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求删除
async function requestDelete(data) {
    SmartLoading.show();
    try {
        let deleteForm = {
            goodsIdList: selectedRowKeyList.value,
        };
        await systemCertificationApi.delete(data.id);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}

// ---------------------------- 批量删除 ----------------------------

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
        if (selectedRowsList.value[0].projectType == PROJECT_TYPE_SYSTEM_ENUM.SUPERVISION1.value) {
            reCertification.value = false;
        }
    } else {
        toSupervision.value = true;
        reCertification.value = true;
    }

}



// 批量删除
function confirmBatchDelete() {
    Modal.confirm({
        title: '提示',
        content: '确定要批量删除这些数据吗?',
        okText: '删除',
        okType: 'danger',
        onOk() {
            requestBatchDelete();
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求批量删除
async function requestBatchDelete() {
    try {
        SmartLoading.show();
        await systemCertificationApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}
function handleResizeColumn(w, col) {
    col.width = w;
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
