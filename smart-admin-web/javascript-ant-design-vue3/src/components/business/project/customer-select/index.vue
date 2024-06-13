<!--
  * 企业列表 下拉选择框 
  * 
  * @Author:    1024创新实验室-主任：卓大 
  * @Date:      2022-08-26 19:16:24 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  1024创新实验室 （ https://1024lab.net ），Since 2012 
  *
-->
<template>
  <a-select v-model:value="selectValue" :style="`width: ${width}`" :placeholder="props.placeholder" :showSearch="true"
    :allowClear="true" :size="size" @change="handleChange" :disabled="disabled" :mode="multiple ? 'multiple' : ''"
    optionFilterProp="label">
    <a-select-option v-for="item in dataList" :key="item.customerId" :label="item.customerName">
      {{ item.customerName }}
    </a-select-option>
  </a-select>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { customerApi } from '/@/api/business/customer/customer-api';

const props = defineProps({
  value: [Number, String, Object],
  width: {
    type: String,
    default: '200px',
  },
  placeholder: {
    type: String,
    default: '请选择',
  },
  size: {
    type: String,
    default: 'default',
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  multiple: {
    type: Boolean,
    default: false,
  },
  type: {
    type: Number,
    default: 1,
  },
});
const emit = defineEmits(['update:value', 'change']);

const selectValue = ref(props.value);

// 箭头value变化
watch(
  () => props.value,
  (newValue) => {
    selectValue.value = newValue;
  }
);

function handleChange(value) {
  emit('update:value', value);
  emit('change', value);
}

const dataList = ref([]);

async function queryData() {
  let res = await customerApi.queryList(props.type);
  dataList.value = res.data;
}
onMounted(queryData);
</script>
