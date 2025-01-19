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
        <a-form :label-col="{ style: { width: '150px' } }">
          <a-row>
            <a-col :span="8">
              <a-form-item label="试验室任务编号" name="taskNo">
                <a-input style="width: 95%" v-model:value="detail.taskNo" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="试验室" name="thirdPartyId">
                <ThirdPartySelect width="95%" v-model:value="detail.thirdPartyId" placeholder="" type="THIRD_1"
                  disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="试验室合同号" name="labContractNo">
                <a-input style="width: 95%" v-model:value="detail.labContractNo" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="试验室合同日期" name="labContractDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.labContractDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="试验费金额" name="labContractAmount">
                <a-input-number style="width: 95%" v-model:value="detail.labContractAmount" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="16">
              <a-form-item label="试验合同备注" name="labContractRemark">
                <a-textarea style="width: 95%" v-model:value="detail.labContractRemark" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="客户要求完成日期" name="labExpectedDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.labExpectedDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="资料发送日期" name="dataSendDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.dataSendDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="资料接收日期" name="dataReceiveDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.dataReceiveDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>

            </a-col>

          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="是否付款" name="isPaid">
                <a-checkbox v-model:checked="detail.isPaid" disabled />
                <!-- <a-input-number style="width: 95%" v-model:value="detail.isPaid" placeholder="是否付款" disabled /> -->
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="付款方" name="payParty">
                <SmartEnumSelect width="95%" v-model:value="detail.payParty" enumName="PAY_PARTY_ENUM" placeholder=""
                  disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="试验费付款日期" name="labPayDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.labPayDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>

            </a-col>

          </a-row>
          <a-row>
            <a-col :span="16">
              <a-form-item label="试验费备注" name="labPayRemark">
                <a-textarea style="width: 95%" v-model:value="detail.labPayRemark" placeholder="" disabled />
              </a-form-item>

            </a-col>

          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="试验室下达任务日期" name="assignTaskDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.assignTaskDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </a-tab-pane>
      <a-tab-pane key="productList" tab="产品列表">
        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="detail.projectProductList" :columns="columns" rowKey="id" bordered
          :loading="tableLoading" :pagination="false">
          <template #bodyCell="{ text, record, column }">
            <template v-if="column.dataIndex === 'productName'">
              <a @click="detailProduct(record.id)">{{ record.productName }}</a>
            </template>
            <template v-if="column.dataIndex === 'status'">
              <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
            </template>

          </template>
        </a-table>
        <!---------- 表格 end ----------->
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="111" />
      </a-tab-pane>

      <a-tab-pane v-if="detail.projectType == 12 || detail.projectType == 11 || detail.projectType == 21" key="qrcode"
        tab="二维码">
        <div class="flex-box">
          <vue-qr class="qr-code" :text="qrCodeUrl" :size="150" />
        </div>
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
import { SmartLoading } from '/@/components/framework/smart-loading';
import DataTracer from '/@/components/support/data-tracer/index.vue';
import { DATA_TRACER_TYPE_ENUM } from '/@/constants/support/data-tracer-const';
import { smartSentry } from '/@/lib/smart-sentry';
import { useRouter } from 'vue-router';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import vueQr from 'vue-qr/src/packages/vue-qr.vue'

const route = useRoute();
let id = ref();
let baseUrl = ref();
// 详情
let detail = ref({});
const qrCodeUrl = ref();
onMounted(() => {
  baseUrl = window.location.origin
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
    let result = await projectLabApi.detail(id.value);
    detail.value = result.data;
    detail.value.customerName = route.query.customerName;
    detail.value.projectType = Number(route.query.projectType);
    detail.value.category = Number(route.query.category);
    detail.isPaid = detail.value.isPaid == 1 ? true : false;
    qrCodeUrl.value = baseUrl + "/#/project/progress?progressCode=" + result.data.progressCode;
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}

// ---------------------------- 表格列 ----------------------------

const columns = ref([
  // {
  //   title: '编号',
  //   dataIndex: 'id',
  //   ellipsis: true,
  // },
  // {
  //   title: '项目ID',
  //   dataIndex: 'projectId',
  //   ellipsis: true,
  // },
  // {
  //   title: '试验室任务ID',
  //   dataIndex: 'taskId',
  //   ellipsis: true,
  // },
  {
    title: '产品名称',
    dataIndex: 'productName',
    ellipsis: true,
  },
  {
    title: '产品型号',
    dataIndex: 'productModel',
    ellipsis: true,
  },
  {
    title: '预计完成日期',
    dataIndex: 'expectedCompletionDate',
    width: 120,
  },
  {
    title: '报告完成日期',
    dataIndex: 'reportCompletionDate',
    width: 120,
  },
  {
    title: '试验室上报日期',
    dataIndex: 'labReportDate',
    ellipsis: true,
  },
  {
    title: '自我声明日期',
    dataIndex: 'selfDeclarationDate',
    ellipsis: true,
  },
  {
    title: '证书编号',
    dataIndex: 'certificateNo',
    ellipsis: true,
  },
  {
    title: '证书发送日期',
    dataIndex: 'certificateSendDate',
    ellipsis: true,
  },
  {
    title: '证书有效期截止日期',
    dataIndex: 'certificateExpiryDate',
    ellipsis: true,
  },

  {
    title: '创建人姓名',
    dataIndex: 'createUserName',
    ellipsis: true,
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
    let queryResult = await projectLabApi.queryPage(queryForm);
    tableData.value = queryResult.data.list;
    total.value = queryResult.data.total;
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    tableLoading.value = false;
  }
}

let router = useRouter();

function detailProduct(id) {
  router.push({ path: '/project/product-detail', query: { id: id } });
  router.push({ path: '/project/product-detail', query: { id: id, customerName: detail.value.customerName, projectType: detail.value.projectType, category: detail.value.category, taskNo: detail.value.taskNo, thirdPartyName: detail.value.thirdPartyName } });
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
