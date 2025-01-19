<!--
  * iframe 组件
  * 
  * @Author:    YY Studio 
  * @Date:      2022-09-01 23:14:49 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
-->
<template>
  <iframe :id="name" :src="url" frameborder="0" height="800" scrolling="yes" width="100%"></iframe>
</template>
<script setup>
let props = defineProps({
  name: String,
  url: String,
});
</script>
