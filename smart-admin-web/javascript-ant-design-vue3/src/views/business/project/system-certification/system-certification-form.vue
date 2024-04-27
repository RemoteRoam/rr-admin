<!--
  * 体系认证表
  *
  * @Author:    cbh
  * @Date:      2024-04-25 14:53:11
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal
      :title="form.id ? '编辑' : '添加'"
      width="500px"
      :open="visibleFlag"
      @cancel="onClose"
      :maskClosable="false"
      :destroyOnClose="true"
    >
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }" >
            <a-row>
                    <a-form-item label="编号"  name="id">
                        <a-input-number style="width: 100%" v-model:value="form.id" placeholder="编号" />
                    </a-form-item>
                    <a-form-item label="原始项目ID"  name="orgProjectId">
                        <a-input-number style="width: 100%" v-model:value="form.orgProjectId" placeholder="原始项目ID" />
                    </a-form-item>
                    <a-form-item label="项目编号"  name="projectNo">
                        <a-input style="width: 100%" v-model:value="form.projectNo" placeholder="项目编号" />
                    </a-form-item>
                    <a-form-item label="项目类型"  name="projectType">
                        <a-input style="width: 100%" v-model:value="form.projectType" placeholder="项目类型" />
                    </a-form-item>
                    <a-form-item label="项目分类"  name="category">
                        <SmartEnumSelect width="100%" v-model:value="form.category" enumName="" placeholder="项目分类"/>
                    </a-form-item>
                    <a-form-item label="客户ID"  name="customerId">
                        <a-input-number style="width: 100%" v-model:value="form.customerId" placeholder="客户ID" />
                    </a-form-item>
                    <a-form-item label="来源分类"  name="sourceType">
                        <SmartEnumSelect width="100%" v-model:value="form.sourceType" enumName="" placeholder="来源分类"/>
                    </a-form-item>
                    <a-form-item label="来源ID"  name="sourceId">
                        <a-input-number style="width: 100%" v-model:value="form.sourceId" placeholder="来源ID" />
                    </a-form-item>
                    <a-form-item label="认证机构ID"  name="thirdPartyId">
                        <a-input-number style="width: 100%" v-model:value="form.thirdPartyId" placeholder="认证机构ID" />
                    </a-form-item>
                    <a-form-item label="合同号"  name="contractNo">
                        <a-input style="width: 100%" v-model:value="form.contractNo" placeholder="合同号" />
                    </a-form-item>
                    <a-form-item label="合同日"  name="contractDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.contractDate" style="width: 100%" placeholder="合同日"/>
                    </a-form-item>
                    <a-form-item label="合同金额"  name="contractAmount">
                        <a-input-number style="width: 100%" v-model:value="form.contractAmount" placeholder="合同金额" />
                    </a-form-item>
                    <a-form-item label="客户预期日期"  name="expectedDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.expectedDate" style="width: 100%" placeholder="客户预期日期"/>
                    </a-form-item>
                    <a-form-item label="备注"  name="remark">
                        <a-input style="width: 100%" v-model:value="form.remark" placeholder="备注" />
                    </a-form-item>
                    <a-form-item label="首款金额"  name="firstPaymentAmount">
                        <a-input-number style="width: 100%" v-model:value="form.firstPaymentAmount" placeholder="首款金额" />
                    </a-form-item>
                    <a-form-item label="首款收款日期"  name="firstPaymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.firstPaymentDate" style="width: 100%" placeholder="首款收款日期"/>
                    </a-form-item>
                    <a-form-item label="资料上报日期"  name="dataReportDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.dataReportDate" style="width: 100%" placeholder="资料上报日期"/>
                    </a-form-item>
                    <a-form-item label="审核老师"  name="auditTeacher">
                        <a-input style="width: 100%" v-model:value="form.auditTeacher" placeholder="审核老师" />
                    </a-form-item>
                    <a-form-item label="审核日期"  name="auditDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.auditDate" style="width: 100%" placeholder="审核日期"/>
                    </a-form-item>
                    <a-form-item label="咨询老师"  name="consultationTeacher">
                        <a-input style="width: 100%" v-model:value="form.consultationTeacher" placeholder="咨询老师" />
                    </a-form-item>
                    <a-form-item label="交卷日期"  name="submissionDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.submissionDate" style="width: 100%" placeholder="交卷日期"/>
                    </a-form-item>
                    <a-form-item label="评定日期"  name="assessmentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.assessmentDate" style="width: 100%" placeholder="评定日期"/>
                    </a-form-item>
                    <a-form-item label="整改日期"  name="rectificationDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.rectificationDate" style="width: 100%" placeholder="整改日期"/>
                    </a-form-item>
                    <a-form-item label="证书发送日期"  name="certificateSendDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.certificateSendDate" style="width: 100%" placeholder="证书发送日期"/>
                    </a-form-item>
                    <a-form-item label="证书有效期截止日期"  name="certificateExpiryDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.certificateExpiryDate" style="width: 100%" placeholder="证书有效期截止日期"/>
                    </a-form-item>
                    <a-form-item label="尾款金额"  name="finalPaymentAmount">
                        <a-input-number style="width: 100%" v-model:value="form.finalPaymentAmount" placeholder="尾款金额" />
                    </a-form-item>
                    <a-form-item label="尾款收款日期"  name="finalPaymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.finalPaymentDate" style="width: 100%" placeholder="尾款收款日期"/>
                    </a-form-item>
                    <a-form-item label="开票日期"  name="invoiceDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.invoiceDate" style="width: 100%" placeholder="开票日期"/>
                    </a-form-item>
                    <a-form-item label="发票金额"  name="invoiceAmount">
                        <a-input-number style="width: 100%" v-model:value="form.invoiceAmount" placeholder="发票金额" />
                    </a-form-item>
                    <a-form-item label="发票号"  name="invoiceNumber">
                        <a-input style="width: 100%" v-model:value="form.invoiceNumber" placeholder="发票号" />
                    </a-form-item>
                    <a-form-item label="邮寄日期"  name="mailingDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.mailingDate" style="width: 100%" placeholder="邮寄日期"/>
                    </a-form-item>
                    <a-form-item label="快递单号"  name="trackingNumber">
                        <a-input style="width: 100%" v-model:value="form.trackingNumber" placeholder="快递单号" />
                    </a-form-item>
            </a-row>

        </a-form>

        <template #footer>
            <a-space>
                <a-button @click="onClose">取消</a-button>
                <a-button type="primary" @click="onSubmit">保存</a-button>
            </a-space>
        </template>
    </a-modal>
</template>
<script setup>
    import { reactive, ref, nextTick } from 'vue';
    import _ from 'lodash';
    import { message } from 'ant-design-vue';
    import { SmartLoading } from '/@/components/framework/smart-loading';
    import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
    import { smartSentry } from '/@/lib/smart-sentry';
    import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';

    // ------------------------ 事件 ------------------------

    const emits = defineEmits(['reloadList']);

    // ------------------------ 显示与隐藏 ------------------------
    // 是否显示
    const visibleFlag = ref(false);

    function show(id) {
        Object.assign(form, formDefault);
        detail(id);
        visibleFlag.value = true;

    }

  async function detail(id) {
    try {
      let result = await systemCertificationApi.detail(id);
      let data = result.data;
      Object.assign(form, data);
      nextTick(() => {        
        formRef.value.clearValidate();
      });
    } catch (error) {
      smartSentry.captureError(error);
    } finally {
      SmartLoading.hide();
    }
  }
    function onClose() {
        Object.assign(form, formDefault);
        visibleFlag.value = false;
    }

    // ------------------------ 表单 ------------------------

    // 组件ref
    const formRef = ref();

    const formDefault = {
        id: undefined,
        id: undefined, //编号
        orgProjectId: undefined, //原始项目ID
        projectNo: undefined, //项目编号
        projectType: undefined, //项目类型
        category: undefined, //项目分类
        customerId: undefined, //客户ID
        sourceType: undefined, //来源分类
        sourceId: undefined, //来源ID
        thirdPartyId: undefined, //认证机构ID
        contractNo: undefined, //合同号
        contractDate: undefined, //合同日
        contractAmount: undefined, //合同金额
        expectedDate: undefined, //客户预期日期
        remark: undefined, //备注
        firstPaymentAmount: undefined, //首款金额
        firstPaymentDate: undefined, //首款收款日期
        dataReportDate: undefined, //资料上报日期
        auditTeacher: undefined, //审核老师
        auditDate: undefined, //审核日期
        consultationTeacher: undefined, //咨询老师
        submissionDate: undefined, //交卷日期
        assessmentDate: undefined, //评定日期
        rectificationDate: undefined, //整改日期
        certificateSendDate: undefined, //证书发送日期
        certificateExpiryDate: undefined, //证书有效期截止日期
        finalPaymentAmount: undefined, //尾款金额
        finalPaymentDate: undefined, //尾款收款日期
        invoiceDate: undefined, //开票日期
        invoiceAmount: undefined, //发票金额
        invoiceNumber: undefined, //发票号
        mailingDate: undefined, //邮寄日期
        trackingNumber: undefined, //快递单号
    };

    let form = reactive({ ...formDefault });

    const rules = {
        id: [{ required: true, message: '编号 必填' }],
    };

    // 点击确定，验证表单
    async function onSubmit() {
        try {
            await formRef.value.validateFields();
            save();
        } catch (err) {
            message.error('参数验证错误，请仔细填写表单数据!');
        }
    }

    // 新建、编辑API
    async function save() {
        SmartLoading.show();
        try {
            if (form.id) {
                await systemCertificationApi.update(form);
            } else {
                await systemCertificationApi.add(form);
            }
            message.success('操作成功');
            emits('reloadList');
            onClose();
        } catch (err) {
            smartSentry.captureError(err);
        } finally {
            SmartLoading.hide();
        }
    }

    defineExpose({
        show,
    });
</script>
