<!--
  * 枚举 下拉框
  * 
  * @Author:    YY Studio 
  * @Date:      2022-08-08 20:32:30 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
  *
-->
<template>
  <a-select v-model:value="selectValue" :style="`width: ${width}`" :placeholder="props.placeholder" :showSearch="true"
    :allowClear="true" :size="size" @change="handleChange" :disabled="disabled">
    <a-select-option v-for="item in $smartEnumPlugin.getValueDescList(props.enumName)" :key="item.value"
      :value="item.value">
      {{ item.desc }}
    </a-select-option>
  </a-select>
</template>

<script setup>
import { ref, watch } from 'vue';

const props = defineProps({
  enumName: String,
  value: [Number, String],
  width: {
    type: String,
    default: '100%',
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
});

const emit = defineEmits(['update:value', 'change']);

const selectValue = ref(props.value);

watch(
  () => props.value,
  (newValue) => {
    selectValue.value = newValue;
  }
);

function handleChange(value) {
  if (value === undefined) {
    selectValue.value = null;
  }
  // console.log(value);
  emit('update:value', value);
  emit('change', value);
}
</script>
