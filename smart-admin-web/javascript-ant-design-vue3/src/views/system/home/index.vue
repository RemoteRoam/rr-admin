<!--
  * 首页
  * 
  * @Author:    YY Studio 
  * @Date:      2022-09-12 22:34:00 
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  YY Studio 
  *
-->
<template>
  <!--  顶部用户信息-->
  <a-row>
    <HomeHeader />
  </a-row>
  <!--下方左右布局-->
  <a-row :gutter="[10, 10]">

  </a-row>
</template>
<script setup>
import { computed } from 'vue';
import HomeHeader from './home-header.vue';
import HomeNotice from './home-notice.vue';
import HomeQuickEntry from './components/quick-entry/home-quick-entry.vue';
import OfficialAccountCard from './components/official-account-card.vue';
import ToBeDoneCard from './components/to-be-done-card.vue';
import ChangelogCard from './components/changelog-card.vue';
import Gauge from './components/echarts/gauge.vue';
import Category from './components/echarts/category.vue';
import Pie from './components/echarts/pie.vue';
import Gradient from './components/echarts/gradient.vue';
import { Modal } from 'ant-design-vue';

// 业绩完成百分比
const saleTargetPercent = computed(() => {
  return 75;
});

</script>
<style lang="less" scoped>
@import './index.less';
</style>
