<!--
  * 商品型号规格 表格 弹窗 选择框 
  * 
-->
<template>
  <a-modal v-model:open="visible" :width="900" title="选择商品规格" @cancel="closeModal" @ok="onSelectSku">
    <a-form class="smart-query-form">
      <a-row class="smart-query-form-row">
        <a-form-item label="商品分类" class="smart-query-form-item">
          <category-tree width="150px" v-model:value="params.categoryId" placeholder="请选择商品分类"
            :categoryType="CATEGORY_TYPE_ENUM.GOODS.value" @change="onCategoryChange" />
        </a-form-item>
        <a-form-item label="商品名称" class="smart-query-form-item">

          <GoodsSelect width="150px" v-model:value="params.goodsId" placeholder="请选择商品" :categoryId="selectedCategoryId"
            @change="onGoodsChange" />
        </a-form-item>
        <a-form-item label="规格型号名称" class="smart-query-form-item">
          <a-input style="width: 150px" v-model:value="params.skuName" placeholder="关键字" />
        </a-form-item>
        <a-form-item class="smart-query-form-item smart-margin-left10">
          <a-button type="primary" @click="onSearch">
            <template #icon>
              <SearchOutlined />
            </template>
            查询
          </a-button>
          <a-button @click="reset" class="smart-margin-left10">
            <template #icon>
              <ReloadOutlined />
            </template>
            重置
          </a-button>
        </a-form-item>
      </a-row>
    </a-form>
    <a-table
      :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, selections: selectedRowsList, columnTitle: ' ', preserveSelectedRowKeys: true }"
      :loading="tableLoading" size="small" :columns="columns" :data-source="tableData" :pagination="false" bordered
      rowKey="skuId" :scroll="{ y: 300 }">
      <template #bodyCell="{ text, column }">
        <template v-if="column.dataIndex === 'disabledFlag'">
          <a-tag :color="text ? 'error' : 'processing'">{{ text ? '禁用' : '启用' }}</a-tag>
        </template>

        <template v-if="column.dataIndex === 'gender'">
          <span>{{ $smartEnumPlugin.getDescByValue('GENDER_ENUM', text) }}</span>
        </template>
      </template>
    </a-table>
    <div class="smart-query-table-page">
      <a-pagination showSizeChanger showQuickJumper show-less-items :pageSizeOptions="PAGE_SIZE_OPTIONS"
        :defaultPageSize="params.pageSize" v-model:current="params.pageNum" v-model:pageSize="params.pageSize"
        :total="total" @change="queryData" @showSizeChange="queryData" :show-total="(total) => `共${total}条`" />
    </div>
  </a-modal>
</template>
<script setup>
import { message } from 'ant-design-vue';
import { computed, reactive, ref } from 'vue';
import { skusApi } from '/@/api/business/goods/skus-api';
import { PAGE_SIZE, PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import CategoryTree from '/@/components/business/category-tree-select/index.vue';
import { CATEGORY_TYPE_ENUM } from '/@/constants/business/erp/category-const';
import GoodsSelect from '/@/components/business/goods-select/index.vue';

// ----------------------- 以下是字段定义 emits props ---------------------
const emits = defineEmits(['selectData']);
defineExpose({
  showModal,
});

// ----------------------- modal  显示与隐藏 ---------------------

const visible = ref(false);
async function showModal(selectedList) {

  let selectedIdList = selectedList.map((e) => e.skuId) || [];
  selectedRowKeyList.value = selectedIdList || [];
  selectedRowsList.value = selectedList || [];
  visible.value = true;
  onSearch();
}
function closeModal() {
  Object.assign(params, defaultParams);
  selectedRowKeyList.value = [];
  selectedRowsList.value = [];
  visible.value = false;
}
// ----------------------- 商品分类变化 ---------------------
const selectedCategoryId = ref(null);
function onCategoryChange(categoryId) {
  selectedCategoryId.value = categoryId;
  onSearch();
}

// ----------------------- 商品变化 ---------------------
function onGoodsChange(goodsId) {
  onSearch();
}

// ----------------------- 员工查询表单与查询 ---------------------
const tableLoading = ref(false);
const total = ref();

let defaultParams = {
  skuId: undefined, //型号规格ID
  goodsId: undefined, //商品ID
  categoryId: undefined, //商品类目ID
  skuName: undefined, //型号规格名称
  pageNum: 1,
  pageSize: 10,
};
const params = reactive({ ...defaultParams });
function reset() {
  Object.assign(params, defaultParams);
  queryData();
}

function onSearch() {
  params.pageNum = 1;
  queryData();
}

async function queryData() {
  tableLoading.value = true;
  try {
    let res = await skusApi.queryPage(params);
    tableData.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    tableLoading.value = false;

    console.log("queryData", selectedRowsList.value);
  }
}

// ----------------------- 员工表格选择 ---------------------
const selectedRowKeyList = ref([]);
const selectedRowsList = ref([]);

function onSelectChange(selectedRowKeys, selections) {
  selectedRowKeyList.value = selectedRowKeys;
  selectedRowsList.value = selections;
}

const hasSelected = computed(() => selectedRowKeyList.value.length > 0);

function onSelectSku() {
  if (!hasSelected.value) {
    message.warning('请选择商品规格型号');
    return;
  }
  emits('selectData', selectedRowsList.value);
  closeModal();
}

// ----------------------- 员工表格渲染 ---------------------
const tableData = ref([]);
//字段
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
]);
</script>
