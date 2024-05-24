<!--
  * 项目产品表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:40:52
  * @Copyright  Remote Nomad Studio
-->
<template>
    <div class="detail-header">
        <a-page-header :title="'实验室任务编号(' + detail.taskNo + ')'">

            <div>
                <a-descriptions size="small" :column="4">
                    <a-descriptions-item label="客户">{{ detail.customerName }}</a-descriptions-item>
                    <a-descriptions-item label="项目类型">{{ $smartEnumPlugin.getDescByValue('PROJECT_TYPE_PRODUCT_ENUM',
                        detail.projectType) }}</a-descriptions-item>
                    <a-descriptions-item label="项目分类">{{ $smartEnumPlugin.getDescByValue('PROJECT_CATEGORY_ENUM',
                        detail.category) }}</a-descriptions-item>
                    <a-descriptions-item label="实验室">{{ detail.thirdPartyName }}</a-descriptions-item>

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
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false"
            :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange, type: 'radio' }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'productName'">
                    <a @click="detailProduct(record.id)">{{ record.productName }}</a>
                </template>
                <template v-if="column.dataIndex === 'status'">
                    <span>{{ $smartEnumPlugin.getDescByValue('PROJECT_STATUS_ENUM', text) }}</span>
                </template>
                <template v-if="column.dataIndex === 'action'">
                    <a-dropdown v-if="record.projectNodeList && record.projectNodeList.length > 0">
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

        <ProjectProductForm ref="formRef" @reloadList="queryData" />
        <ProjectProductAddForm ref="formAddRef" @reloadList="queryData" />

        <LabReportForm ref="labReportFormRef" @reloadList="queryData" />
        <SelfDeclarationForm ref="selfDeclarationFormRef" @reloadList="queryData" />
        <CertificateForm ref="certificateFormRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted, onActivated } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectProductApi } from '/@/api/business/project/project-product-api';
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import NODE_CONST from '/@/constants/business/project/node-const';
import TableOperator from '/@/components/support/table-operator/index.vue';
import ProjectProductForm from './project-product-form.vue';
import ProjectProductAddForm from './project-product-add-form.vue';
import LabReportForm from '../common-nodes/lab-report/lab-report-form.vue';
import SelfDeclarationForm from '../common-nodes/self-declaration/self-declaration-form.vue';
import CertificateForm from '../common-nodes/certificate/certificate-form.vue';
// ---------------------------- 表格列 ----------------------------

const columns = ref([
    // {
    //     title: '编号',
    //     dataIndex: 'id',
    //     ellipsis: true,
    // },
    // {
    //     title: '项目ID',
    //     dataIndex: 'projectId',
    //     ellipsis: true,
    // },
    // {
    //     title: '实验室任务ID',
    //     dataIndex: 'taskId',
    //     ellipsis: true,
    // },
    {
        title: '产品名称',
        dataIndex: 'productName',
        ellipsis: true,
    },
    {
        title: '产品型号',
        dataIndex: 'productModel',
        ellipsis: true,
    },
    {
        title: '实验室上报日期',
        dataIndex: 'labReportDate',
        ellipsis: true,
    },
    {
        title: '自我声明日期',
        dataIndex: 'selfDeclarationDate',
        ellipsis: true,
    },
    {
        title: '证书编号',
        dataIndex: 'certificateNo',
        ellipsis: true,
    },
    {
        title: '证书发送日期',
        dataIndex: 'certificateSendDate',
        ellipsis: true,
    },
    {
        title: '证书有效期截止日期',
        dataIndex: 'certificateExpiryDate',
        ellipsis: true,
    },
    // {
    //     title: '项目认证费表ID',
    //     dataIndex: 'certificationFeeId',
    //     ellipsis: true,
    // },
    // {
    //     title: '项目归档表ID',
    //     dataIndex: 'archiveId',
    //     ellipsis: true,
    // },
    // {
    //     title: '项目邮寄表ID',
    //     dataIndex: 'mail',
    //     ellipsis: true,
    // },
    {
        title: '状态',
        dataIndex: 'status',
        ellipsis: true,
    },
    // {
    //     title: '创建人',
    //     dataIndex: 'createUserId',
    //     ellipsis: true,
    // },
    {
        title: '创建人',
        dataIndex: 'createUserName',
        ellipsis: true,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        ellipsis: true,
    },
    // {
    //     title: '更新人',
    //     dataIndex: 'updateUserId',
    //     ellipsis: true,
    // },
    // {
    //     title: '更新人姓名',
    //     dataIndex: 'updateUserName',
    //     ellipsis: true,
    // },
    // {
    //     title: '更新时间',
    //     dataIndex: 'updateTime',
    //     ellipsis: true,
    // },
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
    projectType: undefined, // 项目类型
    taskId: undefined, //实验室任务ID
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
        let queryResult = await projectProductApi.queryPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

let detail = ref({});
let route = useRoute();

onMounted(() => {
    getTaskDetail(route.query.taskId);
    queryForm.projectId = route.query.projectId;
    queryForm.projectType = route.query.projectType;
    queryForm.taskId = route.query.taskId;
    queryData();
});

onActivated(() => {
    getTaskDetail(route.query.taskId);
    queryForm.projectId = route.query.projectId;
    queryForm.projectType = route.query.projectType;
    queryForm.taskId = route.query.taskId;
    queryData();
});

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();
const formAddRef = ref();

const labReportFormRef = ref();
const selfDeclarationFormRef = ref();
const certificateFormRef = ref();

function showForm() {
    if (selectedRowKeyList.value.length === 1) {
        formRef.value.show(selectedRowKeyList.value[0]);
    } else {
        message.error('请选择一条数据');
    }
}
function showAddForm() {
    detail.value.projectType = route.query.projectType;
    formAddRef.value.show(detail.value);
}

// ---------------------------- 单个删除 ----------------------------
//确认删除
function onDelete(data) {
    Modal.confirm({
        title: '提示',
        content: '确定要删除选吗?',
        okText: '删除',
        okType: 'danger',
        onOk() {
            requestDelete(data);
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求删除
async function requestDelete(data) {
    SmartLoading.show();
    try {
        let deleteForm = {
            goodsIdList: selectedRowKeyList.value,
        };
        await projectProductApi.delete(data.id);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}

// ---------------------------- 批量删除 ----------------------------

// 选择表格行
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
}

// 批量删除
function confirmBatchDelete() {
    Modal.confirm({
        title: '提示',
        content: '确定要批量删除这些数据吗?',
        okText: '删除',
        okType: 'danger',
        onOk() {
            requestBatchDelete();
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求批量删除
async function requestBatchDelete() {
    try {
        SmartLoading.show();
        await projectProductApi.batchDelete(selectedRowKeyList.value);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}

const handleMenuClick = (e, param) => {

    if (e.key.nodeId === NODE_CONST.lab_report) {
        labReportFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.self_declaration) {
        selfDeclarationFormRef.value.show(param, e.key.id);
    } else if (e.key.nodeId === NODE_CONST.certificate) {
        certificateFormRef.value.show(param, e.key.id);
    }

};

async function getTaskDetail(taskId) {
    try {
        let result = await projectLabApi.detail(taskId);
        detail.value = result.data;
        detail.value.customerName = route.query.customerName;
        detail.value.projectType = Number(route.query.projectType);
        detail.value.category = Number(route.query.category);
    } catch (error) {
        smartSentry.captureError(error);
    } finally {
        SmartLoading.hide();
    }
}

let router = useRouter();

function detailProduct(id) {
    router.push({ path: '/project/product-detail', query: { id: id, customerName: detail.value.customerName, projectType: detail.value.projectType, category: detail.value.category, taskNo: detail.value.taskNo, thirdPartyName: detail.value.thirdPartyName } });
}
</script>
<style lang="less" scoped>
.detail-header {
    background-color: #fff;
    padding: 10px;
}
</style>