<!--
  * 销售单表
  *
  * @Author:    cbh
  * @Date:      2024-06-06 13:16:54
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 7 }">
            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="销售类型" name="salesType">
                        <SmartEnumSelect width="100%" v-model:value="form.salesType" enumName="SALES_TYPE_ENUM"
                            placeholder="销售类型" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="供货厂家" name="supplier" v-if="form.salesType == 1">
                        <a-input style="width: 100%" v-model:value="form.supplier" placeholder="供货厂家" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="客户" name="customerId">
                        <CustomerSelect type="2" style="width: 100%" v-model:value="form.customerId" placeholder="客户" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="销售经理" name="managerId">
                        <EmployeeSelect style="width: 100%" v-model:value="form.managerId" placeholder="销售经理" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="合同号" name="contractNo">
                        <a-input style="width: 100%" v-model:value="form.contractNo" placeholder="合同号" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="合同日" name="contractDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.contractDate" style="width: 100%"
                            placeholder="合同日" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="合同金额" name="contractAmount">
                        <a-input-number style="width: 100%" v-model:value="form.contractAmount" placeholder="合同金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="备注" name="remark">
                        <a-textarea style="width: 100%" v-model:value="form.remark" placeholder="备注" />
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
                        <!-- <a-input-number v-model:value="record.quantity" :min="0" :max="record.stockQuantity" /> -->
                        <a-input-number v-model:value="record.quantity" :min="0" />
                    </template>
                    <template v-if="column.dataIndex === 'unitPrice'">
                        <a-input-number v-model:value="record.unitPrice" :min="0" />
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
import { salesApi } from '/@/api/business/goods/sales-api';
import { smartSentry } from '/@/lib/smart-sentry';
import BooleanSelect from '/@/components/framework/boolean-select/index.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';
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

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const tableData = ref([]);
const tableLoading = ref(false);

const formDefault = {
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
    remark: undefined, //备注
    itemList: [],
};

let form = reactive({ ...formDefault });

const rules = {
    salesType: [{ required: true, message: '必须选择销售类型' }],
};

// 点击确定，验证表单
async function onSubmit() {
    try {
        await formRef.value.validateFields();
        // Validate tableData
        for (let record of tableData.value) {
            if (record.quantity === null || record.quantity === undefined || record.unitPrice === null || record.unitPrice === undefined) {
                message.error('销售数量和单价不能为空');
                return;
            }
            if (record.quantity <= 0 || record.unitPrice <= 0) {
                message.error('销售数量和单价必须大于0');
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
        // 判断salesType = 0时， itemList中的record中，quantity是否大于0并且小于库存数量
        if (form.salesType === 0) {
            for (let record of form.itemList) {
                if (record.quantity > record.stockQuantity) {
                    message.error('销售数量超过了库存数量');
                    return;
                }
            }
        }
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
        title: '销售数量',
        dataIndex: 'quantity',
        width: 120,
    },
    {
        title: '销售单价',
        dataIndex: 'unitPrice',
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

defineExpose({
    show,
});
</script>
