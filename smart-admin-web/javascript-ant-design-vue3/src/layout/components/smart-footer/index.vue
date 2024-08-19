<!--
  * 底部：版权、公司等信息
  * 
  * @Author:    YY Studio 
  * @Date:      2022-09-06 20:29:12 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
-->
<template>
  <div class="version">
    <a target="_blank" href="javascript:void(0)"> RemoteNomadStudio V1.0.0 @copyright 【远游工作室】 Since 2024-{{ currentYear
      }} </a>
  </div>
</template>
<script setup>
import dayjs from 'dayjs';

const currentYear = dayjs().year();
</script>

<style lang="less" scoped>
.version {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.45);

  a {
    color: rgba(0, 0, 0, 0.45);
  }

  a:hover {
    color: @primary-color;
  }
}
</style>
