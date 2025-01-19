<!--
  * 体系认证表
  *
  * @Author:    cbh
  * @Date:      2024-04-25 14:53:11
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal title="收款" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 6 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="收款日期" name="paymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.paymentDate" style="width: 100%"
                            placeholder="收款日期" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-item label="收款金额" name="paymentAmount">
                        <a-input-number style="width: 100%" v-model:value="form.paymentAmount" placeholder="收款金额" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-item label="备注" name="remark">
                        <a-input v-model:value="form.remark" placeholder="备注" />
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
import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
import { measurementApi } from '/@/api/business/measurement/measurement-api';
import { salesApi } from '/@/api/business/goods/sales-api';
import { smartSentry } from '/@/lib/smart-sentry';
import NODE_CONST from '/@/constants/business/project/node-const';
import { PROJECT_TYPE_SYSTEM_ENUM } from '/@/constants/business/project/system-certification-const';
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
    projectType: undefined, //项目类型
    projectNodeId: undefined, //项目节点ID
    nodeId: NODE_CONST.first_payment, //节点ID
    nodeStatus: undefined, //节点状态
    passReason: undefined, //跳过原因
    paymentDate: undefined, //收款日期
    paymentAmount: undefined, //收款金额
    remark: undefined, //备注
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


function isProjectTypeInEnum(projectType) {
    return Object.values(PROJECT_TYPE_SYSTEM_ENUM).some(enumItem => enumItem.value === projectType);
}

// 新建、编辑API
async function save(nodeStatus) {
    SmartLoading.show();
    form.nodeStatus = nodeStatus;
    try {
        // console.log('enum', $smartEnumPlugin.getDescByValue('NODE_STATUS_ENUM', text))
        if (isProjectTypeInEnum(form.projectType)) {
            await systemCertificationApi.update(form);
        } else if (form.projectType >= 50 && form.projectType < 60) {
            await measurementApi.update(form);
        } else if (form.projectType >= 60 && form.projectType < 70) {
            await salesApi.update(form);
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
