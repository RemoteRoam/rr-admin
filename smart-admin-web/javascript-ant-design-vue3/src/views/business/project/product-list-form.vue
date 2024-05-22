<!--
  * 项目实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:19:26
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="'产品列表'" width="500px" :open="visibleFlag" @cancel="onClose" :maskClosable="true"
        :destroyOnClose="true">

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false">
            <template #bodyCell="{ text, record, column }">

            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <template #footer>
            <a-space>
                <a-button @click="onClose">取消</a-button>
            </a-space>
        </template>
    </a-modal>
</template>
<script setup>
import { reactive, ref, nextTick, computed } from 'vue';
import { cloneDeep } from 'lodash-es';
import _ from 'lodash';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectProductApi } from '/@/api/business/project/project-product-api';
import { smartSentry } from '/@/lib/smart-sentry';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';


// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(projectId, certificationFeeId, archiveId, mailId) {
    Object.assign(queryForm, queryFormState);

    queryForm.projectId = projectId;
    queryForm.certificationFeeId = certificationFeeId;
    queryForm.archiveId = archiveId;
    queryForm.mailId = mailId;
    queryData();
    visibleFlag.value = true;

}

function onClose() {
    Object.assign(queryForm, queryFormState);
    tableData.value = [];
    visibleFlag.value = false;
}

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
    // {
    //     title: '实验室上报日期',
    //     dataIndex: 'labReportDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '自我声明日期',
    //     dataIndex: 'selfDeclarationDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '证书编号',
    //     dataIndex: 'certificateNo',
    //     ellipsis: true,
    // },
    // {
    //     title: '证书发送日期',
    //     dataIndex: 'certificateSendDate',
    //     ellipsis: true,
    // },
    // {
    //     title: '证书有效期截止日期',
    //     dataIndex: 'certificateExpiryDate',
    //     ellipsis: true,
    // },
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
    // {
    //     title: '状态',
    //     dataIndex: 'status',
    //     ellipsis: true,
    // },
    // {
    //     title: '创建人',
    //     dataIndex: 'createUserId',
    //     ellipsis: true,
    // },
    // {
    //     title: '创建人',
    //     dataIndex: 'createUserName',
    //     ellipsis: true,
    // },
    // {
    //     title: '创建时间',
    //     dataIndex: 'createTime',
    //     ellipsis: true,
    // },
]);

const queryFormState = {
    projectId: undefined, //项目ID
    certificationFeeId: undefined,
    archiveId: undefined,
    mailId: undefined,
    pageNum: 1,
    pageSize: 100,
};
// 查询表单form
const queryForm = reactive({ ...queryFormState });
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);

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


defineExpose({
    show,
});
</script>
