<!--
  * 目录  商品
  * 
  * @Author:    YY Studio 
  * @Date:      2022-08-21 19:52:43 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
-->
<template>
  <div>
    <CategoryTreeTable :category-type="CATEGORY_TYPE_ENUM.GOODS.value" />
  </div>
</template>
<script setup>
import CategoryTreeTable from './components/category-tree-table.vue';
import { CATEGORY_TYPE_ENUM } from '/@/constants/business/erp/category-const';
</script>
