<template>
  <a-modal title="商品详情" :width="600" :open="visible" cancel-text="取消" @cancel="onClose" :footer="null">
    <a-form ref="formRef" :model="form" :label-col="{ span: 5 }" disabled>
      <a-form-item label="商品分类" name="categoryId">
        <CategoryTree v-model:value="form.categoryId" placeholder="请选择商品分类"
          :categoryType="CATEGORY_TYPE_ENUM.GOODS.value" />
      </a-form-item>
      <a-form-item label="商品名称" name="goodsName">
        <a-input v-model:value="form.goodsName" placeholder="请输入商品名称" />
      </a-form-item>

      <a-table bordered :data-source="dataSource" :columns="dynamicColumns" :scroll="{ y: 300 }" :pagination="false">
        <template #bodyCell="{ column, text, record }">
          <template v-if="column.dataIndex === 'skuName'">
            <a-input v-model:value="record.skuName" />
          </template>
          <template v-if="column.dataIndex === 'weight'">
            <a-input-number v-model:value="record.weight" />
          </template>
        </template>
      </a-table>
    </a-form>
  </a-modal>
</template>

<script setup>
import { ref, nextTick, reactive, computed } from 'vue';
import CategoryTree from '/@/components/business/category-tree-select/index.vue';
import { CATEGORY_TYPE_ENUM } from '/@/constants/business/erp/category-const';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { GOODS_STATUS_ENUM } from '/@/constants/business/erp/goods-const';
import { cloneDeep } from 'lodash-es';
import _ from 'lodash';
import { goodsApi } from '/@/api/business/goods/goods-api';
import { smartSentry } from '/@/lib/smart-sentry';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import DictSelect from '/@/components/support/dict-select/index.vue';

// emit
const emit = defineEmits(['reloadList']);

// 组件ref
const formRef = ref();

const formDefault = {
  //商品分类
  categoryId: undefined,
  //商品名称
  goodsName: undefined,
  //备注
  remark: '',
  //商品id
  goodsId: undefined,
  // 规格列表
  skuList: [],
};
let form = reactive({ ...formDefault });

const dataSource = ref([{
  key: '1',
  skuName: '',
  weight: undefined,
}]);

// 是否展示抽屉
const visible = ref(false);

function showDrawer(rowData) {
  Object.assign(form, formDefault);
  detail(rowData.goodsId);
  visible.value = true;

}

async function detail(id) {
  try {
    let result = await goodsApi.detail(id);
    let data = result.data;
    dataSource.value = cloneDeep(data.skuList);
    Object.assign(form, data);
    nextTick(() => {
      formRef.value.clearValidate();
    });
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}

const dynamicColumns = computed(() => {
  const baseColumns = [
    {
      title: '规格型号',
      dataIndex: 'skuName',
      width: '50%',
    },
  ];

  if (form.categoryId === 1) {
    baseColumns.splice(1, 0, {
      title: '重量',
      dataIndex: 'weight',
    });
  }

  return baseColumns;
});


function onClose() {
  Object.assign(form, formDefault);
  dataSource.value = [{
    key: '1',
    skuName: '',
    weight: undefined,
  }];
  visible.value = false;
}


defineExpose({
  showDrawer,
});
</script>