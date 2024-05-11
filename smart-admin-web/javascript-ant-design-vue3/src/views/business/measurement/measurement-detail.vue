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
    <a-page-header :title="'仪器计量(' + detail.projectNo + ')'">

      <div>
        <a-descriptions size="small" :column="4">
          <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
          <a-descriptions-item label="负责人">{{ detail.managerName }}</a-descriptions-item>
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
      <a-tab-pane key="content" tab="详细信息">
        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
          :pagination="false">
          <template #bodyCell="{ text, record, column }">
            <template v-if="column.dataIndex === 'taskNo'">
              <a @click="detailTask(record.id)">{{ record.taskNo }}</a>
            </template>
            <template v-if="column.dataIndex === 'status'">
              <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
            </template>
            <template v-if="column.dataIndex === 'action'">
              <a-dropdown>
                <a class="ant-dropdown-link" @click.prevent>
                  节点操作
                </a>
                <template #overlay>
                  <a-menu @click="handleMenuClick($event, record)">
                    <a-menu-item v-for="node in record.projectNodeList" :key="node">
                      {{ node.nodeName }}
                    </a-menu-item>
                  </a-menu>
                </template>
              </a-dropdown>
            </template>
          </template>
        </a-table>
        <!---------- 表格 end ----------->
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="DATA_TRACER_TYPE_ENUM.MEASUREMENT.value" />
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
import { useRouter } from 'vue-router';

const route = useRoute();
let id = ref();
onMounted(() => {
  console.log('route', route);
  if (route.query.id) {
    id.value = Number(route.query.id);
    getDetail();
    queryForm.projectId = route.query.id;
    queryData();
  }
});
onActivated(() => {
  if (route.query.id) {
    id.value = Number(route.query.id);
    getDetail();
    queryForm.projectId = route.query.id;
    queryData();
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
    let result = await measurementApi.detail(id.value);
    detail.value = result.data;
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}

// ---------------------------- 表格列 ----------------------------

const columns = ref([
  // {
  //     title: '编号',
  //     dataIndex: 'id',
  //     ellipsis: true,
  // },
  {
    title: '实验室任务编号',
    dataIndex: 'taskNo',
    ellipsis: true,
    width: 170,
  },
  // {
  //     title: '项目ID',
  //     dataIndex: 'projectId',
  //     ellipsis: true,
  // },
  {
    title: '实验室',
    dataIndex: 'thirdPartyName',
    ellipsis: true,
  },
  {
    title: '仪器名称',
    dataIndex: 'instrument',
    ellipsis: true,
  },
  {
    title: '有效期',
    dataIndex: 'expiryDate',
    ellipsis: true,
  },
  {
    title: '实验费金额',
    dataIndex: 'experimentAmount',
    ellipsis: true,
  },
  {
    title: '成本价',
    dataIndex: 'costAmount',
    ellipsis: true,
  },
  {
    title: '计量日期',
    dataIndex: 'measurementDate',
    ellipsis: true,
  },
  {
    title: '完成日期',
    dataIndex: 'finishDate',
    ellipsis: true,
  },
  {
    title: '状态',
    dataIndex: 'status',
    ellipsis: true,
  },

  {
    title: '创建人',
    dataIndex: 'createUserName',
    ellipsis: true,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    ellipsis: true,
    width: 160,
  },
]);
const queryFormState = {
  projectId: undefined, //项目ID
  pageNum: 1,
  pageSize: 10,
};
// 查询表单form
const queryForm = reactive({ ...queryFormState });
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);

// 重置查询条件
function resetQuery() {
  let pageSize = queryForm.pageSize;
  Object.assign(queryForm, queryFormState);
  queryForm.pageSize = pageSize;
  queryData();
}

// 查询数据
async function queryData() {
  tableLoading.value = true;
  try {
    let queryResult = await measurementTaskApi.queryPage(queryForm);
    tableData.value = queryResult.data.list;
    total.value = queryResult.data.total;
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    tableLoading.value = false;
  }
}

let router = useRouter();

function detailTask(id) {
  router.push({ path: '/measurement/task/detail', query: { id: id } });
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
