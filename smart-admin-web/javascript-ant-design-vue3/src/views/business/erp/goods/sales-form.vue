<!--
  * 销售单表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:54
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-form-item label="销售单ID" name="id">
                    <a-input-number style="width: 100%" v-model:value="form.id" placeholder="销售单ID" />
                </a-form-item>
                <a-form-item label="销售单号" name="salesNo">
                    <a-input style="width: 100%" v-model:value="form.salesNo" placeholder="销售单号" />
                </a-form-item>
                <a-form-item label="销售类型（0:库存，1:厂家）" name="salesType">
                    <SmartEnumSelect width="100%" v-model:value="form.salesType" enumName=""
                        placeholder="销售类型（0:库存，1:厂家）" />
                </a-form-item>
                <a-form-item label="供货厂家" name="supplier">
                    <a-input style="width: 100%" v-model:value="form.supplier" placeholder="供货厂家" />
                </a-form-item>
                <a-form-item label="客户ID" name="customerId">
                    <a-input-number style="width: 100%" v-model:value="form.customerId" placeholder="客户ID" />
                </a-form-item>
                <a-form-item label="销售经理ID" name="managerId">
                    <a-input-number style="width: 100%" v-model:value="form.managerId" placeholder="销售经理ID" />
                </a-form-item>
                <a-form-item label="合同号" name="contractNo">
                    <a-input style="width: 100%" v-model:value="form.contractNo" placeholder="合同号" />
                </a-form-item>
                <a-form-item label="合同日" name="contractDate">
                    <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.contractDate" style="width: 100%"
                        placeholder="合同日" />
                </a-form-item>
                <a-form-item label="合同金额" name="contractAmount">
                    <a-input-number style="width: 100%" v-model:value="form.contractAmount" placeholder="合同金额" />
                </a-form-item>
                <a-form-item label="回款日期" name="receivedPaymentDate">
                    <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.receivedPaymentDate" style="width: 100%"
                        placeholder="回款日期" />
                </a-form-item>
                <a-form-item label="回款金额" name="receivedPaymentAmount">
                    <a-input-number style="width: 100%" v-model:value="form.receivedPaymentAmount" placeholder="回款金额" />
                </a-form-item>
                <a-form-item label="发货日期" name="shippingDate">
                    <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.shippingDate" style="width: 100%"
                        placeholder="发货日期" />
                </a-form-item>
                <a-form-item label="发货金额" name="shippingAmount">
                    <a-input-number style="width: 100%" v-model:value="form.shippingAmount" placeholder="发货金额" />
                </a-form-item>
                <a-form-item label="开票日期" name="invoiceDate">
                    <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.invoiceDate" style="width: 100%"
                        placeholder="开票日期" />
                </a-form-item>
                <a-form-item label="发票金额" name="invoiceAmount">
                    <a-input-number style="width: 100%" v-model:value="form.invoiceAmount" placeholder="发票金额" />
                </a-form-item>
                <a-form-item label="发票号" name="invoiceNumber">
                    <a-input style="width: 100%" v-model:value="form.invoiceNumber" placeholder="发票号" />
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
import { salesApi } from '/@/api/business/goods/sales-api';
import { smartSentry } from '/@/lib/smart-sentry';
import BooleanSelect from '/@/components/framework/boolean-select/index.vue';
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
    id: undefined, //销售单ID
    salesNo: undefined, //销售单号
    salesType: undefined, //销售类型（0:库存，1:厂家）
    supplier: undefined, //供货厂家
    customerId: undefined, //客户ID
    managerId: undefined, //销售经理ID
    contractNo: undefined, //合同号
    contractDate: undefined, //合同日
    contractAmount: undefined, //合同金额
    receivedPaymentDate: undefined, //回款日期
    receivedPaymentAmount: undefined, //回款金额
    shippingDate: undefined, //发货日期
    shippingAmount: undefined, //发货金额
    invoiceDate: undefined, //开票日期
    invoiceAmount: undefined, //发票金额
    invoiceNumber: undefined, //发票号
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
            await salesApi.update(form);
        } else {
            await salesApi.add(form);
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
