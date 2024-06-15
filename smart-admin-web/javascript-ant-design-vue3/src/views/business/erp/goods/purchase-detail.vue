<template>
    <a-modal :title="'采购单详情'" width="800px" :open="visibleFlag" @cancel="onClose" :footer="null" :destroyOnClose="true">
        <a-form :model="form">
            <a-row>
                <a-col :span="10">
                    <a-form-item label="供货厂家" name="supplier">
                        <a-input style="width: 95%" v-model:value="form.supplier" placeholder="供货厂家" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="14">
                    <a-form-item label="备注" name="remark">
                        <a-input style="width: 95%" v-model:value="form.remark" placeholder="备注" disabled />
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
import { purchaseApi } from '/@/api/business/goods/purchase-api';

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
        title: '采购数量',
        dataIndex: 'quantity',
        width: 150,
    },
    {
        title: '重量',
        dataIndex: 'weight',
        width: 150,
    },

]);

const fetchPurchaseDetail = async (id) => {
    tableLoading.value = true;
    try {
        const response = await purchaseApi.getById(id);
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
    fetchPurchaseDetail(id);
};

const onClose = () => {
    visibleFlag.value = false;
};

defineExpose({
    showDetail,
});
</script>