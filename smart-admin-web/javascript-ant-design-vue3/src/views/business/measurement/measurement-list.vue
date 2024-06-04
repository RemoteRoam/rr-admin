<!--
  * 仪器计量表
  *
  * @Author:    cbh
  * @Date:      2024-05-07 14:42:35
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
            <a-form-item label="负责人" class="smart-query-form-item">
                <EmployeeSelect width="150px" v-model:value="queryForm.sourceId" placeholder="请选择负责人" />
            </a-form-item>
            <a-form-item label="状态" class="smart-query-form-item">
                <SmartEnumSelect width="150px" v-model:value="queryForm.status" enumName="PROJECT_STATUS_ENUM"
                    placeholder="状态" />
            </a-form-item>
            <a-form-item label="操作人" class="smart-query-form-item">
                <EmployeeSelect width="150px" v-model:value="queryForm.createUserId" placeholder="请选择内部员工" />
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
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio' }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'projectNo'">
                    <a @click="detail(record.id)">{{ record.projectNo }}</a>
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
                                    <a-menu-item>
                                        实验室任务
                                    </a-menu-item>
                                    <a-menu-divider />
                                    <a-menu-item v-for="node in record.measurementNodeList" :key="node">
                                        {{ node.nodeName }}
                                    </a-menu-item>
                                </a-menu>
                            </template>
                        </a-dropdown>
                        <!-- <a-button @click="showForm(record)" type="link">编辑</a-button>
                        <a-button @click="onDelete(record)" danger type="link">删除</a-button> -->
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

        <MeasurementFormAdd ref="formAddRef" @reloadList="queryData" />
        <MeasurementForm ref="formRef" @reloadList="queryData" />

        <ReceivePayment ref="receivePaymentFormRef" @reloadList="queryData" />
        <InvoiceForm ref="invoiceFormRef" @reloadList="queryData" />
        <MailForm ref="mailFormRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { measurementApi } from '/@/api/business/measurement/measurement-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import { useRouter } from 'vue-router';
import TableOperator from '/@/components/support/table-operator/index.vue';
import MeasurementForm from './measurement-form.vue';
import MeasurementFormAdd from './measurement-form-add.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import NODE_CONST from '/@/constants/business/project/node-const';
import InvoiceForm from '../common-nodes/invoice/invoice-form.vue';
import MailForm from '../common-nodes/mail/mail-form.vue';
import ReceivePayment from '../common-nodes/receive-payment/receive-payment.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    // {
    //     title: '编号',
    //     dataIndex: 'id',
    //     width: 120,
    // },
    {
        title: '项目编号',
        dataIndex: 'projectNo',
        width: 150,
    },
    // {
    //     title: '项目类型',
    //     dataIndex: 'projectType',
    //     width: 120,
    // },
    {
        title: '客户',
        dataIndex: 'customerName',
        width: 120,
    },
    {
        title: '负责人',
        dataIndex: 'managerName',
        width: 120,
    },
    {
        title: '收款金额',
        dataIndex: 'paymentAmount',
        width: 120,
    },
    {
        title: '收款日期',
        dataIndex: 'paymentDate',
        width: 120,
    },
    // {
    //     title: '备注',
    //     dataIndex: 'remark',
    //     width: 120,
    // },
    {
        title: '开票日期',
        dataIndex: 'invoiceDate',
        width: 120,
    },
    {
        title: '发票金额',
        dataIndex: 'invoiceAmount',
        width: 120,
    },
    // {
    //     title: '发票号',
    //     dataIndex: 'invoiceNumber',
    //     width: 120,
    // },
    {
        title: '邮寄日期',
        dataIndex: 'mailingDate',
        width: 120,
    },
    // {
    //     title: '快递单号',
    //     dataIndex: 'trackingNumber',
    //     width: 120,
    // },
    {
        title: '状态',
        dataIndex: 'status',
        width: 120,
    },
    // {
    //     title: '删除状态',
    //     dataIndex: 'deletedFlag',
    //     width: 120,
    // },
    // {
    //     title: '创建人',
    //     dataIndex: 'createUserId',
    //     width: 120,
    // },
    {
        title: '创建人姓名',
        dataIndex: 'createUserName',
        width: 120,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 120,
    },
    // {
    //     title: '更新人',
    //     dataIndex: 'updateUserId',
    //     width: 120,
    // },
    // {
    //     title: '更新人姓名',
    //     dataIndex: 'updateUserName',
    //     width: 120,
    // },
    // {
    //     title: '更新时间',
    //     dataIndex: 'updateTime',
    //     width: 120,
    // },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 90,
    },
].map(column => ({ ...column, resizable: true })));

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    projectNo: undefined, //项目编号
    customerId: undefined, //客户ID
    managerId: undefined, //负责人ID
    createUserId: undefined, //创建人
    status: undefined, //状态
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
        let queryResult = await measurementApi.queryPage(queryForm);
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


onMounted(queryData);

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();
const formAddRef = ref();

const receivePaymentFormRef = ref();
const invoiceFormRef = ref();
const mailFormRef = ref();

function showForm() {
    if (selectedRowKeyList.value.length === 1) {
        formRef.value.show(selectedRowKeyList.value[0]);
    } else {
        message.error('请选择一条数据');
    }
}
function showFormAdd() {
    formAddRef.value.show();
}

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
        await measurementApi.delete(data.id);
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

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
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
        await measurementApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}


const handleMenuClick = (e, param) => {

    if (e.key === null) {
        router.push({
            path: '/measurement/task/list', query: {
                projectId: param.id, projectNo: param.projectNo, customerName: param.customerName,
                managerName: param.managerName, createUserName: param.createUserName, createTime: param.createTime, date: new Date().getTime()
            }
        });
    } else if (e.key.nodeId === NODE_CONST.receive_payment) {
        receivePaymentFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.invoice) {
        invoiceFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.mail) {
        mailFormRef.value.show(param, e.key.id);
    }
};
function handleResizeColumn(w, col) {
    col.width = w;
}

let router = useRouter();

function detail(id) {
    router.push({ path: '/measurement/detail', query: { id: id, date: new Date().getTime() } });
}

// --------------------------- 导出 ---------------------------
async function exportExcel() {
    await measurementApi.exportExcel(queryForm);
}

</script>
