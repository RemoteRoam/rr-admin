<!--
  * 项目 详情
  * 
-->
<template>
  <div class="detail-header">
    <a-page-header :title="'项目编号(' + detail.projectNo + ')'">

      <div>
        <a-descriptions size="small" :column="4">
          <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
          <a-descriptions-item label="项目类型">{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_PRODUCT_ENUM',
            detail.projectType) }}</a-descriptions-item>
          <a-descriptions-item label="项目分类" v-if="projectTypeGlobal != 31">{{ $smartEnumPlugin.getDescByValue(enumName,
            detail.category) }}</a-descriptions-item>
          <a-descriptions-item label="来源分类">{{ $smartEnumPlugin.getDescByValue('SOURCE_TYPE_ENUM',
            detail.sourceType) }}</a-descriptions-item>
          <a-descriptions-item label="来源">{{ detail.sourceName
            }}</a-descriptions-item>
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
        <a-form :label-col="{ style: { width: '150px' } }" disabled>
          <a-row>
            <a-col :span="8">
              <a-form-item label="上年度审核日期" name="lastYearAuditDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.lastYearAuditDate" style="width: 100%"
                  placeholder="上年度审核日期" />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="审核日期" name="auditDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.auditDate" style="width: 95%"
                  placeholder="审核日期" />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="咨询老师" name="consultationTeacher">
                <a-input style="width: 95%" v-model:value="detail.consultationTeacher" placeholder="咨询老师" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="证书到期日" name="certificateExpiryDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.certificateExpiryDate" style="width: 100%"
                  placeholder="证书到期日" />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="整改内容" name="rectificationContent">
                <a-input style="width: 95%" v-model:value="detail.rectificationContent" placeholder="整改内容" />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="整改日期" name="rectificationDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.rectificationDate" style="width: 95%"
                  placeholder="整改日期" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="16">
              <a-form-item label="备注" name="remark">
                <a-input style="width: 95%" v-model:value="detail.remark" placeholder="备注" />
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="detail.projectType" />
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
import { projectCertificationFeeApi } from '/@/api/business/project/project-certification-fee-api';
import { projectArchiveApi } from '/@/api/business/project/project-archive-api';
import { projectMailApi } from '/@/api/business/project/project-mail-api';
import { SmartLoading } from '/@/components/framework/smart-loading';
import DataTracer from '/@/components/support/data-tracer/index.vue';
import { smartSentry } from '/@/lib/smart-sentry';
import { useRouter } from 'vue-router';

const route = useRoute();
let id = ref();
onMounted(() => {
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

const projectTypeGlobal = ref(null);

const enumName = computed(() => {
  return projectTypeGlobal.value == 21 ? 'LAB_CATEGORY_ENUM' : 'PROJECT_CATEGORY_ENUM';
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
    let result = await projectApi.detail(id.value);
    detail.value = result.data;
    projectTypeGlobal.value = result.data.projectType;
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}

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
const tableDataCertificationFee = ref([]);
const tableDataArchive = ref([]);
const tableDataMail = ref([]);
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
    let queryResult = await projectLabApi.queryPage(queryForm);
    tableData.value = queryResult.data.list;
    total.value = queryResult.data.total;

    let queryResultCertificationFee = await projectCertificationFeeApi.queryPage(queryForm);
    tableDataCertificationFee.value = queryResultCertificationFee.data.list;

    let queryResultArichve = await projectArchiveApi.queryPage(queryForm);
    tableDataArchive.value = queryResultArichve.data.list;

    let queryResultMail = await projectMailApi.queryPage(queryForm);
    tableDataMail.value = queryResultMail.data.list;

  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    tableLoading.value = false;
  }
}

let router = useRouter();

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
