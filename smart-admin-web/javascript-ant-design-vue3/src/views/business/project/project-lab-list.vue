<!--
  * 项目实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:19:26
  * @Copyright  Remote Nomad Studio
-->
<template>
    <div class="detail-header">
        <a-page-header :title="'项目编号(' + detail.projectNo + ')'">

            <div>
                <a-descriptions size="small" :column="4">
                    <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
                    <a-descriptions-item label="项目类型">{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_PRODUCT_ENUM',
                        detail.projectType) }}</a-descriptions-item>
                    <a-descriptions-item label="项目分类">{{ $smartEnumPlugin.getDescByValue(enumName,
                        detail.category) }}</a-descriptions-item>
                    <a-descriptions-item label="来源分类">{{ $smartEnumPlugin.getDescByValue('SOURCE_TYPE_ENUM',
                        detail.sourceType) }}</a-descriptions-item>
                    <a-descriptions-item label="来源">{{ detail.sourceName
                        }}</a-descriptions-item>
                    <a-descriptions-item label="合同号">{{ detail.contractNo }}</a-descriptions-item>
                    <a-descriptions-item label="合同日期">{{ detail.contractDate }}</a-descriptions-item>
                    <a-descriptions-item label="合同金额">{{ detail.contractAmount }}</a-descriptions-item>
                    <a-descriptions-item label="客户预期日期">{{ detail.expectedDate }}</a-descriptions-item>
                    <a-descriptions-item label="创建时间">{{ detail.createTime }}</a-descriptions-item>
                    <a-descriptions-item label="创建人">{{ detail.createUserName }}</a-descriptions-item>

                </a-descriptions>
            </div>
        </a-page-header>
    </div>

    <a-card class="smart-margin-top10" size="small" :bordered="false" :hoverable="true">
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
        <a-table size="small" :dataSource="tableData" :columns="columns" @resizeColumn="handleResizeColumn" rowKey="id"
            bordered :loading="tableLoading" :pagination="false" :scroll="{ x: 2000, y: tableScrollY }"
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio' }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'taskNo'">
                    <a @click="detailTask(record)">{{ record.taskNo }}</a>
                </template>
                <template v-if="column.dataIndex === 'status'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'action'">

                    <a-dropdown>
                        <a class="ant-dropdown-link" @click.prevent>
                            节点操作
                        </a>
                        <template #overlay>
                            <a-menu @click="handleMenuClick($event, record)">
                                <a-menu-item>
                                    产品列表
                                </a-menu-item>
                                <a-menu-divider />
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

        <ProjectLabForm ref="formRef" @reloadList="queryData" />
        <ProjectLabAddForm ref="formAddRef" @reloadList="queryData" />

        <EnterContractForm ref="enterContractFormRef" @reloadList="queryData" />
        <SendDataForm ref="sendDataFormRef" @reloadList="queryData" />
        <ReceiveDataForm ref="receiveDataFormRef" @reloadList="queryData" />
        <PayExperimentFeeForm ref="payExperimentFeeFormRef" @reloadList="queryData" />
        <AssignTaskForm ref="assignTaskFormRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted, onActivated, computed, onBeforeUnmount } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectApi } from '/@/api/business/project/project-api';
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import ProjectLabForm from './project-lab-form.vue';
import ProjectLabAddForm from './project-lab-form-add.vue';
import NODE_CONST from '/@/constants/business/project/node-const';
import EnterContractForm from '../common-nodes/enter-contract/enter-contract-form.vue';
import SendDataForm from '../common-nodes/send-data/send-data-form.vue';
import ReceiveDataForm from '../common-nodes/receive-data/receive-data-form.vue';
import PayExperimentFeeForm from '../common-nodes/pay-experiment-fee/pay-experiment-fee-form.vue';
import AssignTaskForm from '../common-nodes/assign-task/assign-task-form.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    // {
    //     title: '编号',
    //     dataIndex: 'id',
    //     width: 120,
    // },
    // {
    //     title: '项目ID',
    //     dataIndex: 'projectId',
    //     width: 120,
    // },
    {
        title: '实验室任务编号',
        dataIndex: 'taskNo',
        width: 170,
    },
    {
        title: '实验室',
        dataIndex: 'thirdPartyName',
        width: 150,
    },
    {
        title: '实验室合同号',
        dataIndex: 'labContractNo',
        width: 120,
    },
    {
        title: '实验室合同日期',
        dataIndex: 'labContractDate',
        width: 120,
        width: 120,
    },
    {
        title: '实验费金额',
        dataIndex: 'labContractAmount',
        width: 90,
    },
    {
        title: '客户要求完成日期',
        dataIndex: 'labExpectedDate',
        width: 130,
    },
    // {
    //     title: '实验合同备注',
    //     dataIndex: 'labContractRemark',
    //     width: 120,
    // },
    // {
    //     title: '资料发送日期',
    //     dataIndex: 'dataSendDate',
    //     width: 120,
    // },
    // {
    //     title: '资料接收日期',
    //     dataIndex: 'dataReceiveDate',
    //     width: 120,
    // },
    // {
    //     title: '是否付款',
    //     dataIndex: 'isPaid',
    //     width: 120,
    // },
    // {
    //     title: '付款方(客户/我方)',
    //     dataIndex: 'payParty',
    //     width: 120,
    // },
    // {
    //     title: '实验费付款日期',
    //     dataIndex: 'labPayDate',
    //     width: 120,
    // },
    // {
    //     title: '实验费备注',
    //     dataIndex: 'labPayRemark',
    //     width: 120,
    // },
    {
        title: '实验室下达任务日期',
        dataIndex: 'assignTaskDate',
        width: 150,
    },
    // {
    //     title: '预计完成日期',
    //     dataIndex: 'expectedCompletionDate',
    //     width: 120,
    // },
    // {
    //     title: '报告完成日期',
    //     dataIndex: 'reportCompletionDate',
    //     width: 120,
    // },
    {
        title: '状态',
        dataIndex: 'status',
        width: 120,
    },
    {
        title: '创建人',
        dataIndex: 'createUserName',
        width: 120,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 170,
    },
    // {
    //     title: '更新人',
    //     dataIndex: 'updateUserId',
    //     width: 120,
    // },
    // {
    //     title: '更新人姓名',
    //     dataIndex: 'updateUserName',
    //     width: 120,
    // },
    // {
    //     title: '更新时间',
    //     dataIndex: 'updateTime',
    //     width: 120,
    // },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 90,
    },
].map(column => ({ ...column, resizable: true })));

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    projectId: undefined, //项目ID
    projectType: undefined, //项目类型
    thirdPartyId: undefined, //三方机构ID（实验室ID）
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

// 详情
let detail = ref({});
let route = useRoute();

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
        let queryResult = await projectLabApi.queryPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

const tableScrollY = ref(600);
const updateTableScrollY = () => {
    const headerHeight = 240; // 假设的头部高度，根据实际情况调整
    const otherElementsHeight = 180; // 其他元素的总高度，根据实际情况调整
    tableScrollY.value = window.innerHeight - headerHeight - otherElementsHeight;
};

onBeforeUnmount(() => {
    window.removeEventListener('resize', updateTableScrollY);
});

onMounted(() => {
    updateTableScrollY();
    window.addEventListener('resize', updateTableScrollY);
    getDetail(route.query.projectId);
    queryForm.projectId = route.query.projectId;
    queryForm.projectType = route.query.projectType;
    queryData();
});

onActivated(() => {
    getDetail(route.query.projectId);
    queryForm.projectId = route.query.projectId;
    queryForm.projectType = route.query.projectType;
    queryData();
});


const enumName = computed(() => {
    return Number(route.query.projectType) == 21 ? 'LAB_CATEGORY_ENUM' : 'PROJECT_CATEGORY_ENUM';
});

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();
const formAddRef = ref();

const enterContractFormRef = ref();
const sendDataFormRef = ref();
const receiveDataFormRef = ref();
const payExperimentFeeFormRef = ref();
const assignTaskFormRef = ref();


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

// 选择表格行
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
}

const handleMenuClick = (e, param) => {

    param.projectType = 111;
    if (e.key === null) {
        router.push({
            path: '/project/product-list', query: {
                projectId: route.query.projectId,
                customerName: detail.value.customerName, projectType: detail.value.projectType, category: detail.value.category,
                taskId: param.id
            }
        });
    } else if (e.key.nodeId === NODE_CONST.enter_contract) {
        enterContractFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.send_data) {
        sendDataFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.receive_data) {
        receiveDataFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.pay_experiment_fee) {
        payExperimentFeeFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.assign_task) {
        assignTaskFormRef.value.show(param, e.key.id);
    }

};

async function getDetail(projectId) {
    try {
        let result = await projectApi.detail(projectId);
        detail.value = result.data;
    } catch (error) {
        smartSentry.captureError(error);
    } finally {
        SmartLoading.hide();
    }
}
function handleResizeColumn(w, col) {
    col.width = w;
}

let router = useRouter();

function detailTask(record) {
    router.push({ path: '/project/lab-detail', query: { id: record.id, customerName: detail.value.customerName, projectType: detail.value.projectType, category: detail.value.category } });
}
</script>
<style lang="less" scoped>
.detail-header {
    background-color: #fff;
    padding: 10px;
}
</style>
