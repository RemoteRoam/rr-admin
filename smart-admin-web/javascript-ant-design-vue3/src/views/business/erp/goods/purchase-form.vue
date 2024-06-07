<!--
  * 采购入库表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:47
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-form-item label="采购入库ID" name="id">
                    <a-input-number style="width: 100%" v-model:value="form.id" placeholder="采购入库ID" />
                </a-form-item>
                <a-form-item label="采购单号" name="purchaseNo">
                    <a-input style="width: 100%" v-model:value="form.purchaseNo" placeholder="采购单号" />
                </a-form-item>
                <a-form-item label="供货厂家" name="supplier">
                    <a-input style="width: 100%" v-model:value="form.supplier" placeholder="供货厂家" />
                </a-form-item>
                <a-form-item label="删除状态" name="deletedFlag">
                    <BooleanSelect v-model:value="form.deletedFlag" style="width: 100%" />
                </a-form-item>
                <a-form-item label="备注" name="remark">
                    <a-textarea style="width: 100%" v-model:value="form.remark" placeholder="备注" />
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
import { purchaseApi } from '/@/api/business/goods/purchase-api';
import { smartSentry } from '/@/lib/smart-sentry';
import BooleanSelect from '/@/components/framework/boolean-select/index.vue';

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
    id: undefined, //采购入库ID
    purchaseNo: undefined, //采购单号
    supplier: undefined, //供货厂家
    deletedFlag: undefined, //删除状态
    remark: undefined, //备注
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
            await purchaseApi.update(form);
        } else {
            await purchaseApi.add(form);
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
