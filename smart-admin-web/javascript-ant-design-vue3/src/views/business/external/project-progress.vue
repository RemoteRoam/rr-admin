<!--
  * 403 无权限 页面
  * 
  * @Author:    1024创新实验室-主任：卓大 
  * @Date:      2022-08-08 20:46:18
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  1024创新实验室 （ https://1024lab.net ），Since 2012 
-->
<template>
  <div class="container">
    <a-descriptions title="实验室进度" bordered size="small" :labelStyle="{ width: '140px' }">
      <a-descriptions-item label="客户名称">{{ projectData.customerName }}</a-descriptions-item>
      <a-descriptions-item label="实验室名称">{{ projectData.thirdPartyName }}</a-descriptions-item>
      <a-descriptions-item label="资料接收日期">{{ projectData.dataReceiveDate }}</a-descriptions-item>
      <a-descriptions-item label="实验费付款日期">{{ projectData.labPayDate }}</a-descriptions-item>
      <a-descriptions-item label="预计完成日期">{{ projectData.expectedCompletionDate }}</a-descriptions-item>
      <a-descriptions-item label="报告完成日期">{{ projectData.reportCompletionDate }}</a-descriptions-item>
      <a-descriptions-item label="审核任务时间">{{ projectData.auditTaskDate }}</a-descriptions-item>
    </a-descriptions>
    <a-divider>产品列表</a-divider>
    <a-descriptions v-for="product in projectData.projectProductList" :key="product.productName" bordered size="small"
      :labelStyle="{ width: '140px' }" style="margin-bottom: 10px">
      <a-descriptions-item label="产品名称">{{ product.productName }}</a-descriptions-item>
      <a-descriptions-item label="产品型号">{{ product.productModel }}</a-descriptions-item>
      <a-descriptions-item label="自我声明日期">{{ product.selfDeclarationDate }}</a-descriptions-item>
      <a-descriptions-item label="证书发送日期">{{ product.certificateSendDate }}</a-descriptions-item>
      <a-descriptions-item label="认证费付款日期">{{ product.payDate }}</a-descriptions-item>
    </a-descriptions>

  </div>
</template>
<script setup>
import { reactive, ref, onMounted, onActivated } from 'vue';
import { useRoute } from 'vue-router';
import { smartSentry } from '/@/lib/smart-sentry';
import { projectLabApi } from '/@/api/business/project/project-lab-api';

let route = useRoute();
const projectData = ref({
  customerName: undefined,
  thirdPartyName: undefined,
  dataReceiveDate: undefined,
  labPayDate: undefined,
  expectedCompletionDate: undefined,
  reportCompletionDate: undefined,
  auditTaskDate: undefined,
  projectProductList: [
    {
      productName: undefined,
      productModel: undefined,
      selfDeclarationDate: undefined,
      certificateSendDate: undefined,
      payDate: undefined,
    }
  ],
});

// 查询数据
async function queryData(progressCode) {
  try {
    console.log('progressCode', progressCode);
    let queryResult = await projectLabApi.getProgress(progressCode);
    projectData.value = queryResult.data;
    // tableData.value = queryResult.data;
    console.log(queryResult);
  } catch (e) {
    smartSentry.captureError(e);
  }
}

onMounted(() => {
  let progressCode = route.query.progressCode;
  if (!progressCode) {
    return;
  }
  queryData(progressCode);
});

</script>
<style scoped>
.container {
  padding: 16px;
}

.info,
.product-info {
  margin-bottom: 16px;
}

h2,
h3 {
  margin-top: 0;
}

p {
  margin: 4px 0;
}

@media (max-width: 600px) {
  .container {
    padding: 8px;
  }

  p {
    font-size: 14px;
  }

  h2,
  h3 {
    font-size: 18px;
  }
}
</style>