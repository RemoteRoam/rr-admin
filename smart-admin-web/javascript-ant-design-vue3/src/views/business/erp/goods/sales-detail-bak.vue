<template>
    <a-modal :title="'销售单详情'" width="800px" :open="visibleFlag" @cancel="onClose" :footer="null" :destroyOnClose="true">
        <a-form :model="form" disabled>
            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="销售类型" name="salesType">
                        <SmartEnumSelect width="100%" v-model:value="form.salesType" enumName="SALES_TYPE_ENUM"
                            placeholder="销售类型" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="供货厂家" name="supplier" v-if="form.salesType == 1">
                        <a-input style="width: 100%" v-model:value="form.supplier" placeholder="供货厂家" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="客户" name="customerId">
                        <a-input style="width: 100%" v-model:value="form.customerName" placeholder="客户" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="销售经理" name="managerId">
                        <a-input style="width: 100%" v-model:value="form.managerName" placeholder="销售经理" />
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
                <a-col :span="12">
                    <a-form-item label="回款日期" name="receivedPaymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.receivedPaymentDate"
                            style="width: 100%" placeholder="回款日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="回款金额" name="receivedPaymentAmount">
                        <a-input-number style="width: 100%" v-model:value="form.receivedPaymentAmount"
                            placeholder="回款金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="发货日期" name="shippingDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.shippingDate" style="width: 100%"
                            placeholder="发货日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="发货金额" name="shippingAmount">
                        <a-input-number style="width: 100%" v-model:value="form.shippingAmount" placeholder="发货金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="开票日期" name="invoiceDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.invoiceDate" style="width: 100%"
                            placeholder="开票日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="发票金额" name="invoiceAmount">
                        <a-input-number style="width: 100%" v-model:value="form.invoiceAmount" placeholder="发票金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="发票号" name="invoiceNumber">
                        <a-input style="width: 100%" v-model:value="form.invoiceNumber" placeholder="发票号" />
                    </a-form-item>
                </a-col>
            </a-row>

            <a-table :loading="tableLoading" :dataSource="tableData" :columns="columns" :pagination="false"
                rowKey="skuId" size="small" bordered>
                <!-- <template #bodyCell="{ text, record, index, column }">
                    <template v-if="column.dataIndex === 'quantity'">
                        <a-input-number v-model:value="record.quantity" :min="0" disabled />
                    </template>
</template> -->
            </a-table>
        </a-form>
    </a-modal>
</template>
<script setup>
import { ref, reactive } from 'vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import { salesApi } from '/@/api/business/goods/sales-api';

const visibleFlag = ref(false);
let form = ref({});
let tableData = ref([]);
let tableLoading = ref(false);

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

]);

const fetchDetail = async (id) => {
    tableLoading.value = true;
    try {
        const response = await salesApi.getById(id);
        form.value = response.data;
        tableData.value = response.data.itemVOList;
        visibleFlag.value = true;
    } catch (error) {
        console.error(error);
    } finally {
        tableLoading.value = false;
    }
};

const showDetail = (id) => {
    fetchDetail(id);
};

const onClose = () => {
    visibleFlag.value = false;
};

defineExpose({
    showDetail,
});
</script>