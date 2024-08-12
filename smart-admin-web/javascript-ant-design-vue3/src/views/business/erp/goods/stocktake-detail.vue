<template>
    <a-modal :title="'盘点单详情'" width="800px" :open="visibleFlag" @cancel="onClose" :footer="null" :destroyOnClose="true">
        <a-form :model="form" :label-col="{ span: 6 }" disabled>

            <a-row>
                <a-col :span="10">
                    <a-form-item label="盘点单号" name="stocktakeNo">
                        <a-input style="width: 95%" v-model:value="form.stocktakeNo" placeholder="盘点单号" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="盘点单名称" name="title">
                        <a-input style="width: 95%" v-model:value="form.title" placeholder="盘点单名称" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="10">
                    <a-form-item label="创建人" name="createUserName">
                        <a-input style="width: 95%" v-model:value="form.createUserName" placeholder="创建人姓名" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="创建时间" name="createTime">
                        <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.createTime"
                            style="width: 95%" placeholder="创建时间" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="10">
                    <a-form-item label="备注" name="remark">
                        <a-textarea style="width: 95%" v-model:value="form.remark" placeholder="备注" />
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
import _ from 'lodash';
import { ref, reactive } from 'vue';
import { stocktakeApi } from '/@/api/business/goods/stocktake-api';

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
        title: '长度',
        dataIndex: 'length',
        ellipsis: true,
    },
    {
        title: '盘点数量',
        dataIndex: 'quantity',
        width: 150,
    },

]);

const fetchPurchaseDetail = async (id) => {
    tableLoading.value = true;
    try {
        const response = await stocktakeApi.getById(id);
        form.value = response.data;
        tableData.value = response.data.itemList;
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
