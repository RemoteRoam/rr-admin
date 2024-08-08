<!--
  * 仪器计量 详情
  * 
  * @Author:    1024创新实验室-主任：卓大 
  * @Date:      2022-08-15 20:15:49
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  1024创新实验室 （ https://1024lab.net ），Since 2012 
-->
<template>
  <div class="detail-header">
    <a-page-header :title="'仪器计量实验室任务(' + detail.taskNo + ')'">

      <div>
        <a-descriptions size="small" :column="4">
          <a-descriptions-item label="实验室">{{ detail.thirdPartyName }}</a-descriptions-item>
          <a-descriptions-item label="仪器名称">{{ detail.instrument }}</a-descriptions-item>
          <!-- <a-descriptions-item label="实验费金额">{{ detail.experimentAmount }}</a-descriptions-item> -->
          <a-descriptions-item label="成本价">{{ detail.costAmount }}</a-descriptions-item>
          <a-descriptions-item label="计量日期">{{ detail.measurementDate }}</a-descriptions-item>
          <a-descriptions-item label="有效期">{{ detail.expiryDate }}</a-descriptions-item>
          <a-descriptions-item label="完成日期">{{ detail.finishDate }}</a-descriptions-item>
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
        <template v-for="(node, index) in detail.measurementNodeList" key="index">
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
        <DataTracer :dataId="id" :type="DATA_TRACER_TYPE_ENUM.MEASUREMENT_TASK.value" />
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<script setup>
import _ from 'lodash';
import { reactive, onMounted, onActivated, ref } from 'vue';
import { useRoute } from 'vue-router';
import { measurementApi } from '/@/api/business/measurement/measurement-api';
import { measurementTaskApi } from '/@/api/business/measurement/measurement-task-api';
import { SmartLoading } from '/@/components/framework/smart-loading';
import DataTracer from '/@/components/support/data-tracer/index.vue';
import { DATA_TRACER_TYPE_ENUM } from '/@/constants/support/data-tracer-const';
import { smartSentry } from '/@/lib/smart-sentry';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';

const route = useRoute();
let id = ref();
onMounted(() => {
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

//编辑
const operateRef = ref();
function showUpdate() {
  operateRef.value.showModal(id.value);
}

// 详情
let detail = ref({});

async function getDetail() {
  try {
    let result = await measurementTaskApi.detail(id.value);
    detail.value = result.data;
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
