<template>
  <a-modal :title="form.goodsId ? '编辑' : '添加'" :width="600" :open="visible" ok-text="确认" cancel-text="取消" @ok="onSubmit"
    @cancel="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="商品分类" name="categoryId">
        <CategoryTree v-model:value="form.categoryId" placeholder="请选择商品分类"
          :categoryType="CATEGORY_TYPE_ENUM.GOODS.value" />
      </a-form-item>
      <a-form-item label="商品名称" name="goodsName">
        <a-input v-model:value="form.goodsName" placeholder="请输入商品名称" />
      </a-form-item>

      <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="handleAdd">添加规格型号</a-button>
      <a-table bordered :data-source="dataSource" :columns="dynamicColumns" :scroll="{ y: 300 }">
        <template #bodyCell="{ column, text, record }">
          <template v-if="column.dataIndex === 'skuName'">
            <a-input v-model:value="record.skuName" />
          </template>
          <template v-if="column.dataIndex === 'length'">
            <a-input v-model:value="record.length" />
          </template>
          <template v-if="column.dataIndex === 'weight'">
            <a-input-number v-model:value="record.weight" />
          </template>
          <template v-else-if="column.dataIndex === 'operation'">
            <a-popconfirm v-if="dataSource.length" title="确认删除?" @confirm="onDelete(record)">
              <a>删除</a>
            </a-popconfirm>
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
const rules = {
  categoryId: [{ required: true, message: '请选择商品分类' }],
  goodsName: [{ required: true, message: '商品名称不能为空' }],
};

// 是否展示抽屉
const visible = ref(false);

function showDrawer(rowData) {
  Object.assign(form, formDefault);
  if (rowData.goodsId) {
    detail(rowData.goodsId);
  }

  visible.value = true;

}

async function detail(id) {
  try {
    let result = await goodsApi.detail(id);
    let data = result.data;
    dataSource.value = cloneDeep(data.skuList);
    dataSource.value.forEach((item, index) => {
      item.key = index;
    });
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
      width: '40%',
    },
    {
      title: '长度',
      dataIndex: 'length',
      width: '30%',
    },
    {
      title: '操作',
      dataIndex: 'operation',
    },
  ];

  // if (form.categoryId === 1) {
  //   baseColumns.splice(1, 0, {
  //     title: '重量',
  //     dataIndex: 'weight',
  //   });
  // }

  return baseColumns;
});

const dataSource = ref([{
  key: '1',
  skuId: undefined,
  skuName: '',
  length: undefined,
  weight: undefined,
}]);
const count = computed(() => dataSource.value.length + 1);

const onDelete = record => {
  if (record.stockQuantity > 0) {
    message.error('该规格型号已有库存，无法删除');
    return;
  }
  dataSource.value = dataSource.value.filter(item => item.key !== record.key);
};
const handleAdd = () => {
  const newData = {
    key: `${count.value}`,
    skuId: undefined,
    skuName: '',
    length: undefined,
    weight: undefined,
  };
  dataSource.value.push(newData);
};

function onClose() {
  Object.assign(form, formDefault);
  dataSource.value = [{
    key: '1',
    skuId: undefined,
    skuName: '',
    length: undefined,
    weight: undefined,
  }];
  visible.value = false;
}

function onSubmit() {
  formRef.value
    .validate()
    .then(async () => {
      SmartLoading.show();
      try {
        form.skuList = cloneDeep(dataSource.value);
        let params = cloneDeep(form);

        if (form.goodsId) {
          await goodsApi.updateGoods(params);
        } else {
          await goodsApi.addGoods(params);
        }
        message.success(`${form.goodsId ? '修改' : '添加'}成功`);
        onClose();
        emit('reloadList');
      } catch (error) {
        smartSentry.captureError(error);
      } finally {
        SmartLoading.hide();
      }
    })
    .catch((error) => {
      console.log('error', error);
      message.error('参数验证错误，请仔细填写表单数据!');
    });
}

defineExpose({
  showDrawer,
});
</script>