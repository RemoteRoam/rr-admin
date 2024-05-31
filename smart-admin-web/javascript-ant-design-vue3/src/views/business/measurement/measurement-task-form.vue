<!--
  * 仪器计量实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-07 14:44:51
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="实验室任务编号" name="taskNo">
                        <a-input style="width: 100%" v-model:value="form.taskNo" placeholder="实验室任务编号" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="实验室" name="thirdPartyId">
                        <ThirdPartySelect width="100%" v-model:value="form.thirdPartyId" placeholder="请选择实验室"
                            type="THIRD_1" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="仪器名称" name="instrument">
                        <a-textarea rows="4" style="width: 100%" v-model:value="form.instrument" placeholder="仪器名称" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="有效期" name="expiryDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.expiryDate" style="width: 100%"
                            placeholder="有效期" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="实验费金额" name="experimentAmount">
                        <a-input-number style="width: 100%" v-model:value="form.experimentAmount" placeholder="实验费金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="成本价" name="costAmount">
                        <a-input-number style="width: 100%" v-model:value="form.costAmount" placeholder="成本价" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="计量日期" name="measurementDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.measurementDate" style="width: 100%"
                            placeholder="计量日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="完成日期" name="finishDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.finishDate" style="width: 100%"
                            placeholder="完成日期" />
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
import { measurementTaskApi } from '/@/api/business/measurement/measurement-task-api';
import { smartSentry } from '/@/lib/smart-sentry';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';

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
        let result = await measurementTaskApi.detail(id);
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
    taskNo: undefined, //实验室任务编号
    projectId: undefined, //项目ID
    thirdPartyId: undefined, //三方机构ID（实验室ID）
    instrument: undefined, //仪器名称
    expiryDate: undefined, //有效期
    experimentAmount: undefined, //实验费金额
    costAmount: undefined, //成本价
    measurementDate: undefined, //计量日期
    finishDate: undefined, //完成日期
    status: undefined, //状态（ProjectStatusEnum）
    operateUserId: undefined, //操作人
    operateUserName: undefined, //操作人姓名
    operateTime: undefined, //操作时间
    createUserId: undefined, //创建人
    createUserName: undefined, //创建人姓名
    createTime: undefined, //创建时间
    updateUserId: undefined, //更新人
    updateUserName: undefined, //更新人姓名
    updateTime: undefined, //更新时间
};

let form = reactive({ ...formDefault });

const rules = {
    id: [{ required: true, message: '编号 必填' }],
    taskNo: [{ required: true, message: '实验室任务编号 必填' }],
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
            await measurementTaskApi.update(form);
        } else {
            await measurementTaskApi.add(form);
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
