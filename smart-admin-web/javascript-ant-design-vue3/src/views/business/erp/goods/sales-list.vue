<!--
  * 销售单表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:54
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="销售单号" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.salesNo" placeholder="销售单号" />
            </a-form-item>
            <a-form-item label="销售类型" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.salesType" placeholder="销售类型" />
            </a-form-item>
            <a-form-item label="供货厂家" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.supplier" placeholder="供货厂家" />
            </a-form-item>
            <a-form-item label="客户ID" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.customerId" placeholder="客户ID" />
            </a-form-item>
            <a-form-item label="销售经理ID" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.managerId" placeholder="销售经理ID" />
            </a-form-item>
            <a-form-item label="合同号" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.contractNo" placeholder="合同号" />
            </a-form-item>
            <a-form-item label="合同日" class="smart-query-form-item">
                <a-range-picker v-model:value="queryForm.contractDate" :presets="defaultTimeRanges" style="width: 150px"
                    @change="onChangeContractDate" />
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
                <a-button @click="showForm" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
                <a-button @click="confirmBatchDelete" type="danger" size="small"
                    :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <DeleteOutlined />
                    </template>
                    批量删除
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false" :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="showForm(record)" type="link">编辑</a-button>
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

        <SalesForm ref="formRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { salesApi } from '/@/api/business/goods/sales-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import SalesForm from './sales-form.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    {
        title: '销售单ID',
        dataIndex: 'id',
        ellipsis: true,
    },
    {
        title: '销售单号',
        dataIndex: 'salesNo',
        ellipsis: true,
    },
    {
        title: '销售类型（0:库存，1:厂家）',
        dataIndex: 'salesType',
        ellipsis: true,
    },
    {
        title: '供货厂家',
        dataIndex: 'supplier',
        ellipsis: true,
    },
    {
        title: '客户ID',
        dataIndex: 'customerId',
        ellipsis: true,
    },
    {
        title: '销售经理ID',
        dataIndex: 'managerId',
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
        title: '回款日期',
        dataIndex: 'receivedPaymentDate',
        ellipsis: true,
    },
    {
        title: '回款金额',
        dataIndex: 'receivedPaymentAmount',
        ellipsis: true,
    },
    {
        title: '发货日期',
        dataIndex: 'shippingDate',
        ellipsis: true,
    },
    {
        title: '发货金额',
        dataIndex: 'shippingAmount',
        ellipsis: true,
    },
    {
        title: '开票日期',
        dataIndex: 'invoiceDate',
        ellipsis: true,
    },
    {
        title: '发票金额',
        dataIndex: 'invoiceAmount',
        ellipsis: true,
    },
    {
        title: '发票号',
        dataIndex: 'invoiceNumber',
        ellipsis: true,
    },
    {
        title: '备注',
        dataIndex: 'remark',
        ellipsis: true,
    },
    {
        title: '创建人',
        dataIndex: 'createUserId',
        ellipsis: true,
    },
    {
        title: '创建人姓名',
        dataIndex: 'createUserName',
        ellipsis: true,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        ellipsis: true,
    },
    {
        title: '更新人',
        dataIndex: 'updateUserId',
        ellipsis: true,
    },
    {
        title: '更新人姓名',
        dataIndex: 'updateUserName',
        ellipsis: true,
    },
    {
        title: '更新时间',
        dataIndex: 'updateTime',
        ellipsis: true,
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
    salesNo: undefined, //销售单号
    salesType: undefined, //销售类型
    supplier: undefined, //供货厂家
    customerId: undefined, //客户ID
    managerId: undefined, //销售经理ID
    contractNo: undefined, //合同号
    contractDate: [], //合同日
    contractDateBegin: undefined, //合同日 开始
    contractDateEnd: undefined, //合同日 结束
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
        let queryResult = await salesApi.queryPage(queryForm);
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


onMounted(queryData);

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();

function showForm(data) {
    formRef.value.show(data);
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
        await salesApi.delete(data.id);
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
        await salesApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}
</script>
