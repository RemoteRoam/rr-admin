<!--
  * 仪器计量实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-07 14:44:51
  * @Copyright  Remote Nomad Studio
-->
<template>

    <div class="detail-header">
        <a-page-header>

            <div>
                <a-descriptions size="small" :column="5">
                    <a-descriptions-item label="项目编号">{{ detail.projectNo }}</a-descriptions-item>
                    <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
                    <a-descriptions-item label="负责人">{{ detail.managerName }}</a-descriptions-item>
                    <a-descriptions-item label="创建时间">{{ detail.createTime }}</a-descriptions-item>
                    <a-descriptions-item label="创建人">{{ detail.createUserName }}</a-descriptions-item>

                </a-descriptions>
            </div>
        </a-page-header>
    </div>

    <a-card size="small" :bordered="false" :hoverable="true">
        <!---------- 表格操作行 begin ----------->
        <a-row class="smart-table-btn-block">
            <div class="smart-table-operate-block">
                <a-button @click="showAddForm" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
                <a-button @click="showForm" type="primary" size="small" :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <EditOutlined />
                    </template>
                    编辑
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false"
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio' }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'taskNo'">
                    <a @click="detailTask(record.id)">{{ record.taskNo }}</a>
                </template>
                <template v-if="column.dataIndex === 'status'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'action'">
                    <a-dropdown v-if="record.projectNodeList.length > 0">
                        <a class="ant-dropdown-link" @click.prevent>
                            节点操作
                        </a>
                        <template #overlay>
                            <a-menu @click="handleMenuClick($event, record)">
                                <a-menu-item v-for="node in record.projectNodeList" :key="node">
                                    {{ node.nodeName }}
                                </a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>
                </template>
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <div class="smart-query-table-page">
            <a-pagination showSizeChanger showQuickJumper show-less-items :pageSizeOptions="PAGE_SIZE_OPTIONS"
                :defaultPageSize="queryForm.pageSize" v-model:current="queryForm.pageNum"
                v-model:pageSize="queryForm.pageSize" :total="total" @change="queryData" @showSizeChange="queryData"
                :show-total="(total) => `共${total}条`" />
        </div>

        <MeasurementTaskForm ref="formRef" @reloadList="queryData" />
        <MeasurementTaskFormAdd ref="formAddRef" @reloadList="queryData" />

        <MeasurementFinishForm ref="finishFormRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted, onActivated } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { message, Modal } from 'ant-design-vue';
import { measurementTaskApi } from '/@/api/business/measurement/measurement-task-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import MeasurementTaskForm from './measurement-task-form.vue';
import MeasurementTaskFormAdd from './measurement-task-form-add.vue';
import MeasurementFinishForm from './measurement-finish-form.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    // {
    //     title: '编号',
    //     dataIndex: 'id',
    //     ellipsis: true,
    // },
    {
        title: '实验室任务编号',
        dataIndex: 'taskNo',
        ellipsis: true,
        width: 170,
    },
    // {
    //     title: '项目ID',
    //     dataIndex: 'projectId',
    //     ellipsis: true,
    // },
    {
        title: '实验室',
        dataIndex: 'thirdPartyName',
        ellipsis: true,
    },
    {
        title: '仪器名称',
        dataIndex: 'instrument',
        ellipsis: true,
    },
    {
        title: '实验费金额',
        dataIndex: 'experimentAmount',
        ellipsis: true,
    },
    {
        title: '成本价',
        dataIndex: 'costAmount',
        ellipsis: true,
    },
    {
        title: '计量日期',
        dataIndex: 'measurementDate',
        ellipsis: true,
    },
    {
        title: '有效期',
        dataIndex: 'expiryDate',
        ellipsis: true,
    },
    {
        title: '完成日期',
        dataIndex: 'finishDate',
        ellipsis: true,
    },
    {
        title: '状态',
        dataIndex: 'status',
        ellipsis: true,
    },

    {
        title: '创建人',
        dataIndex: 'createUserName',
        ellipsis: true,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        ellipsis: true,
        width: 160,
    },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 90,
    },
]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    projectId: undefined, //项目ID
    pageNum: 1,
    pageSize: 10,
};
// 查询表单form
const queryForm = reactive({ ...queryFormState });
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);

// 重置查询条件
function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryData();
}

// 查询数据
async function queryData() {
    tableLoading.value = true;
    try {
        let queryResult = await measurementTaskApi.queryPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

// 选择表格行
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
}

let detail = ref({});

const route = useRoute();
onMounted(() => {
    detail.value = route.query;
    queryForm.projectId = route.query.projectId;
    queryData();
});

onActivated(() => {
    detail.value = route.query;
    queryForm.projectId = route.query.projectId;
    queryData();
});

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();
const formAddRef = ref();

const finishFormRef = ref();

function showForm() {
    if (selectedRowKeyList.value.length === 1) {
        formRef.value.show(selectedRowKeyList.value[0]);
    } else {
        message.error('请选择一条数据');
    }
}

function showAddForm() {
    formAddRef.value.show(detail.value);
}

const handleMenuClick = (e, param) => {
    finishFormRef.value.show(param, e.key.id)
};

let router = useRouter();

function detailTask(id) {
    router.push({ path: '/measurement/task/detail', query: { id: id } });
}

</script>
<style lang="less" scoped>
.detail-header {
    background-color: #fff;
    padding: 10px;
}
</style>