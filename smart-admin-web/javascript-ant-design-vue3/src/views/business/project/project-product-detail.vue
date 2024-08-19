<!--
  * 项目 详情
  * 
-->
<template>
  <div class="detail-header">
    <a-page-header :title="'试验室任务编号(' + detail.taskNo + ')'">

      <div>
        <a-descriptions size="small" :column="4">
          <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
          <a-descriptions-item label="项目类型">{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_PRODUCT_ENUM',
            detail.projectType) }}</a-descriptions-item>
          <a-descriptions-item label="项目分类">{{ $smartEnumPlugin.getDescByValue(enumName,
            detail.category) }}</a-descriptions-item>
          <a-descriptions-item label="试验室">{{ detail.thirdPartyName }}</a-descriptions-item>
          <a-descriptions-item label="产品名称">{{ detail.productName }}</a-descriptions-item>
          <a-descriptions-item label="产品型号">{{ detail.productModel }}</a-descriptions-item>
          <a-descriptions-item label="预计完成日期">{{ detail.expectedCompletionDate }}</a-descriptions-item>
          <a-descriptions-item label="报告完成日期">{{ detail.reportCompletionDate }}</a-descriptions-item>
          <a-descriptions-item label="试验室上报日期">{{ detail.labReportDate }}</a-descriptions-item>
          <a-descriptions-item label="自我声明日期">{{ detail.selfDeclarationDate }}</a-descriptions-item>
          <a-descriptions-item label="证书编号">{{ detail.certificateNo }}</a-descriptions-item>
          <a-descriptions-item label="证书发送日期">{{ detail.certificateSendDate }}</a-descriptions-item>
          <a-descriptions-item label="证书有效期截止日期">{{ detail.certificateExpiryDate }}</a-descriptions-item>
          <a-descriptions-item label="创建时间">{{ detail.createTime }}</a-descriptions-item>
          <a-descriptions-item label="创建人">{{ detail.createUserName }}</a-descriptions-item>

        </a-descriptions>
      </div>
    </a-page-header>
  </div>
  <a-card class="smart-margin-top10" size="small">
    <a-tabs>
      <a-tab-pane key="nodeList" tab="流程节点">
        <a-row class="row-title">
          <a-col :span="1">
          </a-col>
          <a-col :span="1">
            状态
          </a-col>
          <a-col :span="2">节点名称</a-col>
          <a-col :span="3">操作时间</a-col>
          <a-col :span="2">操作人</a-col>
          <a-col :span="15">跳过原因</a-col>
        </a-row>
        <template v-for="(node, index) in detail.projectNodeList" key="index">
          <a-row style="margin-bottom: 10px">
            <a-col :span="1">
            </a-col>
            <a-col :span="1">
              <ClockCircleOutlined v-if="node.status === 0" />
              <RightCircleOutlined v-else-if="node.status === 1" style="color: blue" />
              <DownCircleOutlined v-else-if="node.status === 2" style="color: green" />
              <CloseCircleOutlined v-else-if="node.status === 3" style="color: red" />
            </a-col>
            <a-col :span="2">{{ node.nodeName }}</a-col>
            <a-col :span="3">{{ node.operateTime }}</a-col>
            <a-col :span="2">{{ node.operateUserName }}</a-col>
            <a-col :span="15">{{ node.passReason }}</a-col>
          </a-row>
        </template>
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="112" />
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<script setup>
import _ from 'lodash';
import { reactive, onMounted, onActivated, ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import { projectApi } from '/@/api/business/project/project-api';
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { projectProductApi } from '/@/api/business/project/project-product-api';
import { SmartLoading } from '/@/components/framework/smart-loading';
import DataTracer from '/@/components/support/data-tracer/index.vue';
import { DATA_TRACER_TYPE_ENUM } from '/@/constants/support/data-tracer-const';
import { smartSentry } from '/@/lib/smart-sentry';
import { useRouter } from 'vue-router';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';

const route = useRoute();
let id = ref();
// 详情
let detail = ref({});
onMounted(() => {
  // console.log('route', route.query);
  if (route.query.id) {
    id.value = Number(route.query.id);
    getDetail();
  }
});
onActivated(() => {
  if (route.query.id) {
    id.value = Number(route.query.id);
    getDetail();
  }
});

const enumName = computed(() => {
  return Number(route.query.projectType) == 21 ? 'LAB_CATEGORY_ENUM' : 'PROJECT_CATEGORY_ENUM';
});

//编辑
const operateRef = ref();
function showUpdate() {
  operateRef.value.showModal(id.value);
}


async function getDetail() {
  try {
    let result = await projectProductApi.detail(id.value);
    detail.value = result.data;
    detail.value.customerName = route.query.customerName;
    detail.value.projectType = Number(route.query.projectType);
    detail.value.category = Number(route.query.category);
    detail.value.taskNo = route.query.taskNo;
    detail.value.thirdPartyName = route.query.thirdPartyName;
    // console.log("get detail:", detail.value)
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}


</script>

<style lang="less" scoped>
.detail-header {
  background-color: #fff;
  padding: 10px;
}

.row-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px
}
</style>
