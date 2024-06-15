<!--
  * 盘点表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:54
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="盘点单号" class="smart-query-form-item">
                <a-input style="width: 190px" v-model:value="queryForm.stocktakeNo" placeholder="盘点单号" />
            </a-form-item>
            <a-form-item label="盘点单名称" class="smart-query-form-item">
                <a-input style="width: 190px" v-model:value="queryForm.title" placeholder="盘点单名称" />
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
                <a-button @click="showForm" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
                <!-- <a-button @click="confirmBatchDelete" type="danger" size="small"
                    :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <DeleteOutlined />
                    </template>
                    批量删除
                </a-button> -->
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'stocktakeNo'">
                    <a @click="showDetail(record.id)">{{ text }}</a>
                </template>
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

        <StocktakeForm ref="formRef" @reloadList="queryData" />
        <StocktakeDetail ref="detailRef" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { stocktakeApi } from '/@/api/business/goods/stocktake-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import StocktakeForm from './stocktake-form.vue';
import StocktakeDetail from './stocktake-detail.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    // {
    //     title: '盘点ID',
    //     dataIndex: 'id',
    //     ellipsis: true,
    // },
    {
        title: '盘点单号',
        dataIndex: 'stocktakeNo',
        ellipsis: true,
    },
    {
        title: '盘点单名称',
        dataIndex: 'title',
        ellipsis: true,
    },
    // {
    //     title: '删除状态',
    //     dataIndex: 'deletedFlag',
    //     ellipsis: true,
    // },
    {
        title: '备注',
        dataIndex: 'remark',
        ellipsis: true,
    },

    {
        title: '创建人',
        dataIndex: 'createUserName',
        ellipsis: true,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        ellipsis: true,
    },

]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    stocktakeNo: undefined, //盘点单号
    createTime: undefined, //盘点时间
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
        let queryResult = await stocktakeApi.queryPage(queryForm);
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


const detailRef = ref();

function showDetail(id) {
    // 使用$refs调用showDetail方法
    detailRef.value.showDetail(id);
}

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
        await stocktakeApi.delete(data.id);
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
        await stocktakeApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}
</script>
