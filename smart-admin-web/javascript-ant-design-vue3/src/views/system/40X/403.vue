<!--
  * 403 无权限 页面
  * 
  * @Author:    YY Studio 
  * @Date:      2022-08-08 20:46:18
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
-->
<template>
  <a-result status="403" title="对不起，您没有权限访问此内容">
    <template #extra>
      <a-button type="primary" @click="goHome">返回首页</a-button>
    </template>
  </a-result>
</template>
<script setup>
import { useRouter } from 'vue-router';
import { HOME_PAGE_NAME } from '/@/constants/system/home-const';

const router = useRouter();
function goHome() {
  router.push({ name: HOME_PAGE_NAME });
}
</script>
