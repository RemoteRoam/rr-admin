<!--
  * 库存表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:42:51
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-form-item label="库存ID" name="id">
                    <a-input-number style="width: 100%" v-model:value="form.id" placeholder="库存ID" />
                </a-form-item>
                <a-form-item label="型号规格ID" name="skuId">
                    <a-input-number style="width: 100%" v-model:value="form.skuId" placeholder="型号规格ID" />
                </a-form-item>
                <a-form-item label="商品ID" name="goodsId">
                    <a-input-number style="width: 100%" v-model:value="form.goodsId" placeholder="商品ID" />
                </a-form-item>
                <a-form-item label="商品类目ID" name="categoryId">
                    <a-input-number style="width: 100%" v-model:value="form.categoryId" placeholder="商品类目ID" />
                </a-form-item>
                <a-form-item label="SKU库存数量" name="quantity">
                    <a-input-number style="width: 100%" v-model:value="form.quantity" placeholder="SKU库存数量" />
                </a-form-item>
                <a-form-item label="删除状态" name="deletedFlag">
                    <BooleanSelect v-model:value="form.deletedFlag" style="width: 100%" />
                </a-form-item>
                <a-form-item label="备注" name="remark">
                    <a-input style="width: 100%" v-model:value="form.remark" placeholder="备注" />
                </a-form-item>
                <a-form-item label="更新时间" name="updateTime">
                    <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.updateTime"
                        style="width: 100%" placeholder="更新时间" />
                </a-form-item>
                <a-form-item label="创建时间" name="createTime">
                    <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.createTime"
                        style="width: 100%" placeholder="创建时间" />
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
import { inventoryApi } from '/@/api/business/goods/inventory-api';
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
    id: undefined, //库存ID
    skuId: undefined, //型号规格ID
    goodsId: undefined, //商品ID
    categoryId: undefined, //商品类目ID
    quantity: undefined, //SKU库存数量
    deletedFlag: undefined, //删除状态
    remark: undefined, //备注
    updateTime: undefined, //更新时间
    createTime: undefined, //创建时间
};

let form = reactive({ ...formDefault });

const rules = {
    skuId: [{ required: true, message: '型号规格ID 必填' }],
    goodsId: [{ required: true, message: '商品ID 必填' }],
    categoryId: [{ required: true, message: '商品类目ID 必填' }],
    quantity: [{ required: true, message: 'SKU库存数量 必填' }],
    deletedFlag: [{ required: true, message: '删除状态 必填' }],
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
            await inventoryApi.update(form);
        } else {
            await inventoryApi.add(form);
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
