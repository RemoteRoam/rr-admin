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
          <a-descriptions-item label="合同号">{{ detail.contractNo }}</a-descriptions-item>
          <a-descriptions-item label="合同日期">{{ detail.contractDate }}</a-descriptions-item>
          <a-descriptions-item label="合同金额">{{ detail.contractAmount }}</a-descriptions-item>
          <a-descriptions-item label="客户预期日期">{{ detail.expectedDate }}</a-descriptions-item>
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
              <a-form-item label="首款金额" name="firstPaymentAmount">
                <a-input-number style="width: 95%" v-model:value="detail.firstPaymentAmount" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="首款收款日期" name="firstPaymentDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.firstPaymentDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="审核任务时间" name="auditTaskDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.auditTaskDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="审核老师" name="auditTeacher">
                <a-input style="width: 95%" v-model:value="detail.auditTeacher" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="咨询老师" name="consultationTeacher">
                <a-input style="width: 95%" v-model:value="detail.consultationTeacher" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="审核日期" name="auditDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.auditDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="整改内容" name="rectificationContent">
                <a-input style="width: 95%" v-model:value="detail.rectificationContent" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="整改日期" name="rectificationDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.rectificationDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8" v-if="detail.projectType === 31">
              <a-form-item label="抽检实验完成日期" name="samplingTestDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.samplingTestDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="尾款金额" name="finalPaymentAmount">
                <a-input-number style="width: 95%" v-model:value="detail.finalPaymentAmount" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="尾款收款日期" name="finalPaymentDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.finalPaymentDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="开票日期" name="invoiceDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.invoiceDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="发票金额" name="invoiceAmount">
                <a-input-number style="width: 95%" v-model:value="detail.invoiceAmount" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="发票号" name="invoiceNumber">
                <a-input style="width: 95%" v-model:value="detail.invoiceNumber" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="创建人" name="createUserName">
                <a-input style="width: 95%" v-model:value="detail.createUserName" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="创建时间" name="createTime">
                <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="detail.createTime"
                  style="width: 95%" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label=" 更新人" name="updateUserName">
                <a-input style="width: 95%" v-model:value="detail.updateUserName" placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="更新时间" name="updateTime">
                <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="detail.updateTime"
                  style="width: 95%" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>

          <a-row>
            <a-col :span="16">
              <a-form-item label="备注" name="remark">
                <a-textarea style="width: 95%" v-model:value="detail.remark" placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
        <div class="smart-margin-top10" width="500px">
          <!---------- 认证费表格 begin ----------->
          <div v-if="detail.projectType === 11 || detail.projectType === 12">
            <h3 class="table-title smart-margin-top10">认证费</h3>
            <a-table size="small" :dataSource="tableDataCertificationFee" :columns="columnsCertificationFee" rowKey="id"
              bordered :pagination="false">
              <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'id'">
                  <a @click="showCertificationFeeProduct(record.id)">{{ record.id }}</a>
                </template>
                <template v-if="column.dataIndex === 'isPaid'">
                  {{ record.isPaid ? '是' : '否' }}
                </template>
                <template v-if="column.dataIndex === 'payParty'">
                  <span>{{ $smartEnumPlugin.getDescByValue('PAY_PARTY_ENUM', text) }}</span>
                </template>
              </template>
            </a-table>
          </div>
          <!---------- 表格 end ----------->
          <!---------- 归档表格 begin ----------->
          <h3 class="table-title smart-margin-top10">归档</h3>
          <a-table size="small" :dataSource="tableDataArchive" :columns="columnsArchive" rowKey="id" bordered
            :pagination="false">
            <template #bodyCell="{ text, record, column }">
              <template v-if="column.dataIndex === 'id'">
                <a @click="showArchiveProduct(record.id)">{{ record.id }}</a>
              </template>
              <!-- <template v-if="column.dataIndex === 'projectId'">
              <a @click="detailTask(record.id)">{{ record.projectId }}</a>
            </template> -->
            </template>
          </a-table>
          <!---------- 表格 end ----------->
          <!---------- 邮寄表格 begin ----------->
          <h3 class="table-title smart-margin-top10">邮寄</h3>
          <a-table size="small" :dataSource="tableDataMail" :columns="columnsMail" rowKey="id" bordered
            :pagination="false">
            <template #bodyCell="{ text, record, column }">
              <template v-if="column.dataIndex === 'id'">
                <a @click="showMailProduct(record.id)">{{ record.id }}</a>
              </template>
              <!-- <template v-if="column.dataIndex === 'projectId'">
              <a @click="detailTask(record.id)">{{ record.projectId }}</a>
            </template> -->
            </template>
          </a-table>
          <!---------- 表格 end ----------->
        </div>
      </a-tab-pane>
      <a-tab-pane key="labtask" tab="实验室任务">
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
          </template>
        </a-table>
        <!---------- 表格 end ----------->
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="detail.projectType" />
      </a-tab-pane>
    </a-tabs>

    <ProductListForm ref="productListFormRef" />
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
import ProductListForm from './product-list-form.vue';

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


const productListFormRef = ref();

function showCertificationFeeProduct(feeId) {
  productListFormRef.value.show(id.value, feeId, null, null);
}

function showArchiveProduct(archiveId) {
  productListFormRef.value.show(id.value, null, archiveId, null);
}

function showMailProduct(mailId) {
  productListFormRef.value.show(id.value, null, null, mailId);
}
// ---------------------------- 表格列 ----------------------------

const columns = ref([
  // {
  //     title: '编号',
  //     dataIndex: 'id',
  //     ellipsis: true,
  // },
  // {
  //     title: '项目ID',
  //     dataIndex: 'projectId',
  //     ellipsis: true,
  // },
  {
    title: '实验室任务编号',
    dataIndex: 'taskNo',
    width: 170,
  },
  {
    title: '实验室',
    dataIndex: 'thirdPartyName',
    width: 150,
  },
  {
    title: '实验室合同号',
    dataIndex: 'labContractNo',
    width: 120,
  },
  {
    title: '实验室合同日期',
    dataIndex: 'labContractDate',
    ellipsis: true,
    width: 120,
  },
  {
    title: '实验费金额',
    dataIndex: 'labContractAmount',
    width: 90,
  },
  {
    title: '客户要求完成日期',
    dataIndex: 'labExpectedDate',
    width: 130,
  },
  // {
  //     title: '实验合同备注',
  //     dataIndex: 'labContractRemark',
  //     ellipsis: true,
  // },
  // {
  //     title: '资料发送日期',
  //     dataIndex: 'dataSendDate',
  //     ellipsis: true,
  // },
  // {
  //     title: '资料接收日期',
  //     dataIndex: 'dataReceiveDate',
  //     ellipsis: true,
  // },
  // {
  //     title: '是否付款',
  //     dataIndex: 'isPaid',
  //     ellipsis: true,
  // },
  // {
  //     title: '付款方(客户/我方)',
  //     dataIndex: 'payParty',
  //     ellipsis: true,
  // },
  // {
  //     title: '实验费付款日期',
  //     dataIndex: 'labPayDate',
  //     ellipsis: true,
  // },
  // {
  //     title: '实验费备注',
  //     dataIndex: 'labPayRemark',
  //     ellipsis: true,
  // },
  {
    title: '实验室下达任务日期',
    dataIndex: 'assignTaskDate',
    width: 150,
  },
  // {
  //     title: '预计完成日期',
  //     dataIndex: 'expectedCompletionDate',
  //     ellipsis: true,
  // },
  // {
  //     title: '报告完成日期',
  //     dataIndex: 'reportCompletionDate',
  //     ellipsis: true,
  // },
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
    width: 170,
  },
  // {
  //     title: '更新人',
  //     dataIndex: 'updateUserId',
  //     ellipsis: true,
  // },
  // {
  //     title: '更新人姓名',
  //     dataIndex: 'updateUserName',
  //     ellipsis: true,
  // },
  // {
  //     title: '更新时间',
  //     dataIndex: 'updateTime',
  //     ellipsis: true,
  // },
  // {
  //   title: '操作',
  //   dataIndex: 'action',
  //   fixed: 'right',
  //   width: 90,
  // },
]);


const columnsCertificationFee = ref([
  {
    title: '编号',
    dataIndex: 'id',
    ellipsis: true,
  },
  // {
  //   title: '项目ID',
  //   dataIndex: 'projectId',
  //   ellipsis: true,
  // },
  {
    title: '是否付款',
    dataIndex: 'isPaid',
    ellipsis: true,
  },
  {
    title: '付款方(客户/我方)',
    dataIndex: 'payParty',
    ellipsis: true,
  },
  {
    title: '认证费付款日期',
    dataIndex: 'payDate',
    ellipsis: true,
  },
  {
    title: '认证费备注',
    dataIndex: 'payRemark',
    ellipsis: true,
  },
  // {
  //   title: '创建人',
  //   dataIndex: 'createUserId',
  //   ellipsis: true,
  // },
  {
    title: '创建人',
    dataIndex: 'createUserName',
    ellipsis: true,
  },

]);


const columnsArchive = ref([
  {
    title: '编号',
    dataIndex: 'id',
    ellipsis: true,
  },
  // {
  //   title: '项目ID',
  //   dataIndex: 'projectId',
  //   ellipsis: true,
  // },
  {
    title: '归档位置',
    dataIndex: 'archivePosition',
    ellipsis: true,
  },
  {
    title: '归档日期',
    dataIndex: 'archiveDate',
    ellipsis: true,
  },
  // {
  //   title: '创建人',
  //   dataIndex: 'createUserId',
  //   ellipsis: true,
  // },
  {
    title: '创建人',
    dataIndex: 'createUserName',
    ellipsis: true,
  },
]);


const columnsMail = ref([
  {
    title: '编号',
    dataIndex: 'id',
    ellipsis: true,
  },
  // {
  //   title: '项目ID',
  //   dataIndex: 'projectId',
  //   ellipsis: true,
  // },
  {
    title: '邮寄日期',
    dataIndex: 'mailingDate',
    ellipsis: true,
  },
  {
    title: '快递单号',
    dataIndex: 'trackingNumber',
    ellipsis: true,
  },
  // {
  //   title: '创建人',
  //   dataIndex: 'createUserId',
  //   ellipsis: true,
  // },
  {
    title: '创建人',
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

function detailTask(id) {
  router.push({ path: '/project/lab-detail', query: { id: id, customerName: detail.value.customerName, projectType: detail.value.projectType, category: detail.value.category } });
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
