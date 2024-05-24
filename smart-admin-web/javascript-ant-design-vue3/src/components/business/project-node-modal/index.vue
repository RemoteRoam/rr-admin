<template>
    <a-modal :title="modalTitle" width="400px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <!-- 可变的表单内容 -->
            <slot name="formContent"></slot>
        </a-form>

        <template #footer>
            <a-space>
                <a-button type="primary" @click="onJump">跳过</a-button>
                <a-button type="primary" @click="onSubmit">保存</a-button>
                <a-button @click="onClose">取消</a-button>
            </a-space>
        </template>
    </a-modal>
</template>
<script setup>
import { reactive, ref, h, defineEmits, watch, nextTick } from 'vue';
import _ from 'lodash';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
import { smartSentry } from '/@/lib/smart-sentry';
import NODE_CONST from '/@/constants/business/project/node-const';

const emits = defineEmits(['reloadList']);

const visibleFlag = ref(false);
const formRef = ref();
const formDefault = {
    id: undefined,
    projectType: undefined,
    nodeId: undefined,
    status: undefined,
    passReason: undefined,
    firstPaymentDate: undefined,
    firstPaymentAmount: undefined,
};
let form = reactive({ ...formDefault });
const rules = {};


const show = (rowData) => {
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
        Object.assign(form, rowData);
    }
    visibleFlag.value = true;
    nextTick(() => {
        formRef.value.clearValidate();
    });
};

const onClose = () => {
    Object.assign(form, formDefault);
    visibleFlag.value = false;
};

const onSubmit = async () => {
    try {
        await formRef.value.validateFields();
        save(2);
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
};

const onJump = () => {
    Modal.confirm({
        title: '跳过',
        content: h('textarea', {
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
};

const save = async (status) => {
    SmartLoading.show();
    form.status = status;
    try {
        if (form.id) {
            await systemCertificationApi.update(form);
        } else {
            // await systemCertificationApi.add(form);
        }
        message.success('操作成功');
        emits('reloadList');
        onClose();
    } catch (err) {
        smartSentry.captureError(err);
    } finally {
        SmartLoading.hide();
    }
};

const modalTitle = '确认首款'; // 可变的标题

// 可变的节点ID，默认为 undefined
let nodeId = NODE_CONST.first_payment;

// 监听节点ID的变化
watch(() => nodeId, (newValue) => {
    form.nodeId = newValue;
});

defineExpose({
    show,
});

</script>
