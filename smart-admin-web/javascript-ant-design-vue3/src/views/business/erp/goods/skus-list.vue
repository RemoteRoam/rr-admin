<!--
  * 型号规格
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:37:19
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="商品分类" class="smart-query-form-item">
                <category-tree width="150px" v-model:value="queryForm.categoryId" placeholder="请选择商品分类"
                    :categoryType="CATEGORY_TYPE_ENUM.GOODS.value" @change="onCategoryChange" />
            </a-form-item>
            <a-form-item label="商品名称" class="smart-query-form-item">

                <GoodsSelect width="150px" v-model:value="queryForm.goodsId" placeholder="请选择商品"
                    :categoryId="selectedCategoryId" />
            </a-form-item>
            <a-form-item label="规格型号名称" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.skuName" placeholder="关键字" />
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
            <a-button @click="exportExcel()" type="primary" size="small" v-privilege="'goods:skus:excel'">
                <template #icon>
                    <FileExcelOutlined />
                </template>
                导出数据
            </a-button>
            <!-- <div class="smart-table-operate-block">
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
            </div> -->
            <div class="smart-table-setting-block">
                <!-- <TableOperator v-model="columns" :tableId="null" :refresh="queryData" /> -->
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="skuId" bordered :loading="tableLoading"
            :pagination="false">
            <template #bodyCell="{ text, record, column }">
                <!-- <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="showForm(record)" type="link">编辑</a-button>
                        <a-button @click="onDelete(record)" danger type="link">删除</a-button>
                    </div>
                </template> -->
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <div class="smart-query-table-page">
            <a-pagination showSizeChanger showQuickJumper show-less-items :pageSizeOptions="PAGE_SIZE_OPTIONS"
                :defaultPageSize="queryForm.pageSize" v-model:current="queryForm.pageNum"
                v-model:pageSize="queryForm.pageSize" :total="total" @change="queryData" @showSizeChange="queryData"
                :show-total="(total) => `共${total}条`" />
        </div>

        <SkusForm ref="formRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { skusApi } from '/@/api/business/goods/skus-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import SkusForm from './skus-form.vue';
import CategoryTree from '/@/components/business/category-tree-select/index.vue';
import { CATEGORY_TYPE_ENUM } from '/@/constants/business/erp/category-const';
import GoodsSelect from '/@/components/business/goods-select/index.vue';

// ---------------------------- 表格列 ----------------------------

const columns = ref([
    {
        title: '商品类目',
        dataIndex: 'categoryName',
        ellipsis: true,
    },
    {
        title: '商品名称',
        dataIndex: 'goodsName',
        ellipsis: true,
    },
    {
        title: '型号规格名称',
        dataIndex: 'skuName',
        ellipsis: true,
    },
    {
        title: '库存数量',
        dataIndex: 'stockQuantity',
        width: 100,
    },
]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    skuId: undefined, //型号规格ID
    goodsId: undefined, //商品ID
    categoryId: undefined, //商品类目ID
    skuName: undefined, //型号规格名称
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
        let queryResult = await skusApi.queryPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}
// ----------------------- 商品分类变化 ---------------------
const selectedCategoryId = ref(null);
function onCategoryChange(categoryId) {
    selectedCategoryId.value = categoryId;
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
        await skusApi.delete(data.skuId);
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
        await skusApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}
// --------------------------- 导出 ---------------------------
async function exportExcel() {
    await skusApi.exportExcel(queryForm);
}
</script>
