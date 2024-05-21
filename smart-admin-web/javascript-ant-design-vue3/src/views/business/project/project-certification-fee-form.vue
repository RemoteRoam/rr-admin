<!--
  * 项目认证费表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:46:16
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="500px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-form-item label="编号" name="id">
                    <a-input-number style="width: 100%" v-model:value="form.id" placeholder="编号" />
                </a-form-item>
                <a-form-item label="项目ID" name="projectId">
                    <a-input-number style="width: 100%" v-model:value="form.projectId" placeholder="项目ID" />
                </a-form-item>
                <a-form-item label="是否付款" name="isPaid">
                    <a-input-number style="width: 100%" v-model:value="form.isPaid" placeholder="是否付款" />
                </a-form-item>
                <a-form-item label="付款方" name="payParty">
                    <SmartEnumSelect width="100%" v-model:value="form.payParty" enumName="PAY_PARTY_ENUM"
                        placeholder="付款方" />
                </a-form-item>
                <a-form-item label="认证费付款日期" name="payDate">
                    <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.payDate" style="width: 100%"
                        placeholder="认证费付款日期" />
                </a-form-item>
                <a-form-item label="认证费备注" name="payRemark">
                    <a-input style="width: 100%" v-model:value="form.payRemark" placeholder="认证费备注" />
                </a-form-item>
                <a-form-item label="创建人" name="createUserId">
                    <a-input-number style="width: 100%" v-model:value="form.createUserId" placeholder="创建人" />
                </a-form-item>
                <a-form-item label="创建人姓名" name="createUserName">
                    <a-input style="width: 100%" v-model:value="form.createUserName" placeholder="创建人姓名" />
                </a-form-item>
                <a-form-item label="创建时间" name="createTime">
                    <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.createTime"
                        style="width: 100%" placeholder="创建时间" />
                </a-form-item>
                <a-form-item label="更新人" name="updateUserId">
                    <a-input-number style="width: 100%" v-model:value="form.updateUserId" placeholder="更新人" />
                </a-form-item>
                <a-form-item label="更新人姓名" name="updateUserName">
                    <a-input style="width: 100%" v-model:value="form.updateUserName" placeholder="更新人姓名" />
                </a-form-item>
                <a-form-item label="更新时间" name="updateTime">
                    <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.updateTime"
                        style="width: 100%" placeholder="更新时间" />
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
import { projectCertificationFeeApi } from '/@/api/business/project-certification-fee/project-certification-fee-api';
import { smartSentry } from '/@/lib/smart-sentry';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
        Object.assign(form, rowData);
    }
    visibleFlag.value = true;
    nextTick(() => {
        formRef.value.clearValidate();
    });
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
    projectId: undefined, //项目ID
    isPaid: undefined, //是否付款
    payParty: undefined, //付款方(客户/我方)
    payDate: undefined, //认证费付款日期
    payRemark: undefined, //认证费备注
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
            await projectCertificationFeeApi.update(form);
        } else {
            await projectCertificationFeeApi.add(form);
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
