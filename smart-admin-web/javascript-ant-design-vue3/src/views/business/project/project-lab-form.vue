<!--
  * 项目实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:19:26
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="60%" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 8 }">
            <a-row>
                <a-col :span="8">
                    <a-form-item label="实验室任务编号" name="taskNo">
                        <a-input style="width: 95%" v-model:value="form.taskNo" placeholder="实验室任务编号" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="实验室" name="thirdPartyId">
                        <ThirdPartySelect width="95%" v-model:value="form.thirdPartyId" placeholder="请选择实验室"
                            type="THIRD_1" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="实验室合同号" name="labContractNo">
                        <a-input style="width: 95%" v-model:value="form.labContractNo" placeholder="实验室合同号" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="实验室合同日期" name="labContractDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.labContractDate" style="width: 95%"
                            placeholder="实验室合同日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="实验费金额" name="labContractAmount">
                        <a-input-number style="width: 95%" v-model:value="form.labContractAmount" placeholder="实验费金额" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="16">
                    <a-form-item label="实验合同备注" name="labContractRemark">
                        <a-textarea style="width: 95%" v-model:value="form.labContractRemark" placeholder="实验合同备注" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="客户要求完成日期" name="labExpectedDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.labExpectedDate" style="width: 95%"
                            placeholder="客户要求完成日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="资料发送日期" name="dataSendDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.dataSendDate" style="width: 95%"
                            placeholder="资料发送日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="资料接收日期" name="dataReceiveDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.dataReceiveDate" style="width: 95%"
                            placeholder="资料接收日期" />
                    </a-form-item>

                </a-col>

            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="是否付款" name="isPaid">
                        <a-input-number style="width: 95%" v-model:value="form.isPaid" placeholder="是否付款" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="付款方" name="payParty">
                        <SmartEnumSelect width="95%" v-model:value="form.payParty" enumName="PAY_PARTY_ENUM"
                            placeholder="" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="实验费付款日期" name="labPayDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.labPayDate" style="width: 95%"
                            placeholder="实验费付款日期" />
                    </a-form-item>

                </a-col>

            </a-row>
            <a-row>
                <a-col :span="16">
                    <a-form-item label="实验费备注" name="labPayRemark">
                        <a-textarea style="width: 95%" v-model:value="form.labPayRemark" placeholder="实验费备注" />
                    </a-form-item>

                </a-col>

            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="实验室下达任务日期" name="assignTaskDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.assignTaskDate" style="width: 95%"
                            placeholder="实验室下达任务日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="预计完成日期" name="expectedCompletionDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.expectedCompletionDate"
                            style="width: 95%" placeholder="预计完成日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="报告完成日期" name="reportCompletionDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.reportCompletionDate"
                            style="width: 95%" placeholder="报告完成日期" />
                    </a-form-item>
                </a-col>
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
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { smartSentry } from '/@/lib/smart-sentry';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
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
        let result = await projectLabApi.detail(id);
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
    id: undefined, //编号
    projectId: undefined, //项目ID
    taskNo: undefined, //实验室任务编号
    thirdPartyId: undefined, //三方机构ID（实验室ID）
    labContractNo: undefined, //实验室合同号
    labContractDate: undefined, //实验室合同日期
    labContractAmount: undefined, //实验费金额
    labExpectedDate: undefined, //客户要求完成日期
    labContractRemark: undefined, //实验合同备注
    dataSendDate: undefined, //资料发送日期
    dataReceiveDate: undefined, //资料接收日期
    isPaid: undefined, //是否付款
    payParty: undefined, //付款方(客户/我方)
    labPayDate: undefined, //实验费付款日期
    labPayRemark: undefined, //实验费备注
    assignTaskDate: undefined, //实验室下达任务日期
    expectedCompletionDate: undefined, //预计完成日期
    reportCompletionDate: undefined, //报告完成日期
    createUserId: undefined, //创建人
    createUserName: undefined, //创建人姓名
    createTime: undefined, //创建时间
    updateUserId: undefined, //更新人
    updateUserName: undefined, //更新人姓名
    updateTime: undefined, //更新时间
};

let form = reactive({ ...formDefault });

const rules = {
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
            await projectLabApi.update(form);
        } else {
            await projectLabApi.add(form);
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
