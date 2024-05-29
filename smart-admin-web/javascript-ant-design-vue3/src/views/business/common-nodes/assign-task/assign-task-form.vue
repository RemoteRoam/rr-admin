<!--
  * 体系认证表
  *
  * @Author:    cbh
  * @Date:      2024-04-25 14:53:11
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal title="下达任务" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 7 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="实验室下达任务日期" name="assignTaskDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.assignTaskDate" style="width: 95%"
                            placeholder="实验室下达任务日期" />
                    </a-form-item>
                </a-col>
            </a-row>

        </a-form>

        <template #footer>
            <a-space>
                <a-button type="primary" @click="onJump">跳过</a-button>
                <a-button type="primary" @click="onSubmit">保存</a-button>
                <a-button @click="onClose">取消</a-button>
            </a-space>
        </template>
    </a-modal>
    <!-- <JumpNodeForm ref="jumpNodeRef" @closeForm="onClose" /> -->
</template>
<script setup>
import { reactive, ref, nextTick, h } from 'vue';
import _ from 'lodash';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { smartSentry } from '/@/lib/smart-sentry';
import NODE_CONST from '/@/constants/business/project/node-const';
// import { JumpNodeForm } from '../jump-node/jump-node-form.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

const jumpNodeRef = ref();

function show(rowData, projectNodeId) {
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
        Object.assign(form, rowData);
        form.projectNodeId = projectNodeId;
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
    id: undefined, //项目ID
    projectId: undefined, //项目类型
    projectType: undefined, //项目类型
    projectNodeId: undefined, //项目节点ID
    nodeId: NODE_CONST.assign_task, //节点ID
    nodeStatus: 0, //节点状态
    passReason: undefined, //跳过原因
    assignTaskDate: undefined, //实验室下达任务日期
};

let form = reactive({ ...formDefault });

const rules = {
    // id: [{ required: true, message: '编号 必填' }],
};

// 点击确定，验证表单
async function onSubmit() {
    try {
        await formRef.value.validateFields();
        save(2);
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
}

// 点击跳过，弹出Modal.confirm框，输入跳过原因，值赋给form.passReason，点击确认后调用save方法，参数nodeStatus传3

function onJump() {
    // 用另一种方式处理，暂时有问题，后续再调查
    // jumpNodeRef.value.show(form);

    Modal.confirm({
        title: '跳过',
        content:
            h('textarea', {
                style: { marginTop: '20px', width: '80%' },
                placeholder: '请输入跳过原因',
                'onUpdate:modelValue': value => {
                    form.passReason = value;
                },
                value: form.passReason,
                onInput: event => {
                    form.passReason = event.target.value;
                }
            }),
        okText: '确认',
        cancelText: '取消',
        onOk() {
            save(3);
        },
        onCancel() {
            console.log('Cancel');
        },
    });
}



// 新建、编辑API
async function save(nodeStatus) {
    SmartLoading.show();
    form.nodeStatus = nodeStatus;
    try {
        await projectLabApi.update(form);
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
