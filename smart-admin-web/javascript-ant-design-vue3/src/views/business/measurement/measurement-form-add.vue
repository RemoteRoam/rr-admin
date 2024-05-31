<!--
  * 仪器计量表
  *
  * @Author:    cbh
  * @Date:      2024-05-07 14:42:35
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="客户" name="customerId">
                        <CustomerSelect width="70%" v-model:value="form.customerId" placeholder="请选择客户" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-item label="负责人" name="managerId">
                        <EmployeeSelect width="70%" v-model:value="form.managerId" placeholder="请选择负责人" />
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
import { measurementApi } from '/@/api/business/measurement/measurement-api';
import { smartSentry } from '/@/lib/smart-sentry';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';

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
    customerId: undefined, //客户ID
    managerId: undefined, //负责人ID
    paymentAmount: undefined, //收款金额
    paymentDate: undefined, //收款日期
    remark: undefined, //备注
    invoiceDate: undefined, //开票日期
    invoiceAmount: undefined, //发票金额
    invoiceNumber: undefined, //发票号
    mailingDate: undefined, //邮寄日期
    trackingNumber: undefined, //快递单号
    status: undefined, //状态
    deletedFlag: undefined, //删除状态
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
            await measurementApi.update(form);
        } else {
            await measurementApi.add(form);
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
