<!--
  * 体系认证 详情
  * 
  * @Author:    1024创新实验室-主任：卓大 
  * @Date:      2022-08-15 20:15:49
  * @Wechat:    zhuda1024 
  * @Email:     lab1024@163.com 
  * @Copyright  1024创新实验室 （ https://1024lab.net ），Since 2012 
-->
<template>
  <div class="detail-header">
    <a-page-header
      :title="$smartEnumPlugin.getDescByValue('PROJECT_TYPE_SYSTEM_ENUM', detail.projectType) + '(' + detail.projectNo + ')'">

      <div>
        <a-descriptions size="small" :column="4">
          <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
          <a-descriptions-item label="类别">{{ detail.category }}</a-descriptions-item>
          <a-descriptions-item label="来源分类">{{ $smartEnumPlugin.getDescByValue('SOURCE_TYPE_ENUM',
            detail.sourceType) }}</a-descriptions-item>
          <a-descriptions-item label="来源">{{ detail.sourceName
            }}</a-descriptions-item>
          <a-descriptions-item label="认证机构">{{ detail.thirdPartyName }}</a-descriptions-item>
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
        <template v-for="(node, index) in detail.systemCertificationNodeList" key="index">
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
              <a-form-item label="合同号" name="contractNo">
                <a-input style="width: 95%" v-model:value="detail.contractNo" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="合同日" name="contractDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.contractDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="合同金额" name="contractAmount">
                <a-input-number style="width: 95%" v-model:value="detail.contractAmount" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="认证机构" name="thirdPartyId">
                <ThirdPartySelect width="95%" v-model:value="detail.thirdPartyId" type="THIRD_2" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="客户预期日期" name="expectedDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.expectedDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="首款金额" name="firstPaymentAmount">
                <a-input-number style="width: 95%" v-model:value="detail.firstPaymentAmount" disabled />
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
              <a-form-item label="前期资料传递日期" name="preDataTransferDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.preDataTransferDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="体系文件传递日期" name="systemFileTransferDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.systemFileTransferDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="资料上报日期" name="dataReportDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.dataReportDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="审核老师" name="auditTeacher">
                <a-input style="width: 95%" v-model:value="detail.auditTeacher" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="审核日期" name="auditDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.auditDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="咨询老师" name="consultationTeacher">
                <a-input style="width: 95%" v-model:value="detail.consultationTeacher" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="交卷日期" name="submissionDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.submissionDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="评定日期" name="assessmentDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.assessmentDate" style="width: 95%"
                  placeholder="" disabled />
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
            <a-col :span="8">
              <a-form-item label="证书发送日期" name="certificateSendDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.certificateSendDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="有效期截止日期" name="certificateExpiryDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.certificateExpiryDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="尾款金额" name="finalPaymentAmount">
                <a-input-number style="width: 95%" v-model:value="detail.finalPaymentAmount" disabled />
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
                <a-input-number style="width: 95%" v-model:value="detail.invoiceAmount" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label="发票号" name="invoiceNumber">
                <a-input style="width: 95%" v-model:value="detail.invoiceNumber" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-item label="邮寄日期" name="mailingDate">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="detail.mailingDate" style="width: 95%"
                  placeholder="" disabled />
              </a-form-item>
            </a-col>


            <a-col :span="8">
              <a-form-item label="快递单号" name="trackingNumber">
                <a-input style="width: 95%" v-model:value="detail.trackingNumber" disabled />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="16">
              <a-form-item label="备注" name="remark">
                <a-input style="width: 95%" v-model:value="detail.remark" disabled />
              </a-form-item>
            </a-col>
            <a-col :span="8">
            </a-col>
          </a-row>
        </a-form>
      </a-tab-pane>

      <a-tab-pane key="dataTracer" tab="变更记录">
        <DataTracer :dataId="id" :type="DATA_TRACER_TYPE_ENUM.SYSTEM_CERTIFICATION.value" />
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<script setup>
import _ from 'lodash';
import { computed, onMounted, onActivated, ref } from 'vue';
import { useRoute } from 'vue-router';
import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
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
    let result = await systemCertificationApi.detail(id.value);
    detail.value = result.data;
  } catch (error) {
    smartSentry.captureError(error);
  } finally {
    SmartLoading.hide();
  }
}

// 地区
const area = computed(() => {
  let area = '';
  if (!detail.value) {
    return area;
  }
  if (detail.value.provinceName) {
    area = area + detail.value.provinceName;
  }
  if (detail.value.cityName) {
    area = area + detail.value.cityName;
  }
  if (detail.value.districtName) {
    area = area + detail.value.districtName;
  }
  return area;
});

const logo = computed(() => {
  if (!detail.value) {
    return '';
  }
  if (!_.isEmpty(detail.value.enterpriseLogo)) {
    return detail.value.enterpriseLogo[0].fileUrl;
  }
  return '';
});
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
