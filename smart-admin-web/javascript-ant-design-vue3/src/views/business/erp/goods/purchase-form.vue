<!--
  * 采购入库表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:47
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '新建采购单'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules">
            <a-row>
                <a-col :span="10">
                    <a-form-item label="供货厂家" name="supplier">
                        <a-input style="width: 95%" v-model:value="form.supplier" placeholder="供货厂家" />
                    </a-form-item>
                </a-col>
                <a-col :span="14">
                    <a-form-item label="备注" name="remark">
                        <a-input style="width: 95%" v-model:value="form.remark" placeholder="备注" />
                    </a-form-item>
                </a-col>
            </a-row>

            <div>
                <a-button class="button-style" type="primary" @click="addSku"> 添加商品 </a-button>
            </div>

            <a-table :loading="tableLoading" :dataSource="tableData" :columns="columns" :pagination="false"
                rowKey="skuId" size="small" bordered>
                <template #bodyCell="{ text, record, index, column }">
                    <template v-if="column.dataIndex === 'quantity'">
                        <a-input-number v-model:value="record.quantity" :min="0" />
                    </template>
                    <template v-if="column.dataIndex === 'weight'">
                        <a-input-number v-model:value="record.weight" :min="0" />
                    </template>
                    <template v-else-if="column.dataIndex === 'operate'">
                        <a @click="deleteRecord(record.skuId)">移除</a>
                    </template>
                </template>
            </a-table>
            <SkuTableSelectModal ref="selectSkuModal" @selectData="selectData" />
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
import { cloneDeep } from 'lodash-es';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { purchaseApi } from '/@/api/business/goods/purchase-api';
import { smartSentry } from '/@/lib/smart-sentry';
import BooleanSelect from '/@/components/framework/boolean-select/index.vue';
import SkuTableSelectModal from '/@/components/business/sku-table-select-modal/index.vue';

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
    tableData.value = [];
    visibleFlag.value = false;
}

const total = ref(0);
const tableData = ref([]);
const tableLoading = ref(false);

// 添加商品型号规格
const selectSkuModal = ref();
async function addSku() {
    // let res = await enterpriseApi.employeeList([props.enterpriseId]);
    selectSkuModal.value.showModal(tableData.value);
}

function deleteRecord(skuId) {
    tableData.value = tableData.value.filter(record => record.skuId !== skuId);
}

const columns = reactive([
    {
        title: '商品分类',
        dataIndex: 'categoryName',
        width: 100,
    },
    {
        title: '商品名称',
        dataIndex: 'goodsName',
        ellipsis: true,
    },
    {
        title: '型号规格',
        dataIndex: 'skuName',
        ellipsis: true,
    },
    {
        title: '采购数量',
        dataIndex: 'quantity',
        width: 150,
    },
    {
        title: '重量',
        dataIndex: 'weight',
        width: 150,
    },
    {
        title: '操作',
        dataIndex: 'operate',
        width: 60,
    },
]);

function selectData(list) {
    if (_.isEmpty(list)) {
        message.warning('请选择商品型号规格');
        return;
    }
    tableData.value = list;
    console.log(tableData.value);

}

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const formDefault = {
    supplier: undefined, //供货厂家
    remark: undefined, //备注
    itemList: [],
};

let form = reactive({ ...formDefault });

const rules = {
};

// 点击确定，验证表单
async function onSubmit() {
    try {
        await formRef.value.validateFields();
        // Check that each record in tableData has a quantity value
        for (let record of tableData.value) {
            if (record.quantity === null || record.quantity === undefined) {
                message.error('商品的采购数量不能为空');
                return;
            }
            if (record.quantity === 0) {
                message.error('商品的采购数量不能为0');
                return;
            }
        }
        save();
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
}

// 新建、编辑API
async function save() {
    SmartLoading.show();
    try {

        form.itemList = cloneDeep(tableData.value);
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
