<!--
  * 项目产品表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:40:52
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 8 }">
            <a-row>
                <a-col :span="18">
                    <a-form-item label="产品名称" name="productName">
                        <a-input style="width: 100%" v-model:value="form.productName" placeholder="产品名称" />
                    </a-form-item>
                </a-col>

                <a-col :span="18">
                    <a-form-item label="产品型号" name="productModel">
                        <a-textarea style="width: 100%" v-model:value="form.productModel" placeholder="产品型号" />
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
import { projectProductApi } from '/@/api/business/project/project-product-api';
import { smartSentry } from '/@/lib/smart-sentry';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
    Object.assign(form, formDefault);
    form.projectId = rowData.projectId;
    form.projectType = rowData.projectType;
    form.taskId = rowData.id;
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
    projectId: undefined, //项目ID
    projectType: undefined, //项目类型
    taskId: undefined, //实验室任务ID
    productName: undefined, //产品名称
    productModel: undefined, //产品型号
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
        await projectProductApi.add(form);
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
