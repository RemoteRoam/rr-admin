<!--
  * 仪器计量实验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-07 14:44:51
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="项目ID" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.projectId" placeholder="项目ID" />
            </a-form-item>
            <a-form-item class="smart-query-form-item">
                <a-button type="primary" @click="queryData">
                    <template #icon>
                        <SearchOutlined />
                    </template>
                    查询
                </a-button>
                <a-button @click="resetQuery" class="smart-margin-left10">
                    <template #icon>
                        <ReloadOutlined />
                    </template>
                    重置
                </a-button>
            </a-form-item>
        </a-row>
    </a-form>
    <!---------- 查询表单form end ----------->

    <a-card size="small" :bordered="false" :hoverable="true">
        <!---------- 表格操作行 begin ----------->
        <a-row class="smart-table-btn-block">
            <div class="smart-table-operate-block">
                <a-button @click="showForm" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table
                size="small"
                :dataSource="tableData"
                :columns="columns"
                rowKey="id"
                bordered
                :loading="tableLoading"
                :pagination="false"
        >
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="showForm(record)" type="link">编辑</a-button>
                    </div>
                </template>
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <div class="smart-query-table-page">
            <a-pagination
                    showSizeChanger
                    showQuickJumper
                    show-less-items
                    :pageSizeOptions="PAGE_SIZE_OPTIONS"
                    :defaultPageSize="queryForm.pageSize"
                    v-model:current="queryForm.pageNum"
                    v-model:pageSize="queryForm.pageSize"
                    :total="total"
                    @change="queryData"
                    @showSizeChange="queryData"
                    :show-total="(total) => `共${total}条`"
            />
        </div>

        <MeasurementTaskForm  ref="formRef" @reloadList="queryData"/>

    </a-card>
</template>
<script setup>
    import { reactive, ref, onMounted } from 'vue';
    import { message, Modal } from 'ant-design-vue';
    import { SmartLoading } from '/@/components/framework/smart-loading';
    import { measurementTaskApi } from '/@/api/business/measurement-task/measurement-task-api';
    import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
    import { smartSentry } from '/@/lib/smart-sentry';
    import TableOperator from '/@/components/support/table-operator/index.vue';
    import MeasurementTaskForm from './measurement-task-form.vue';
    // ---------------------------- 表格列 ----------------------------

    const columns = ref([
        {
            title: '编号',
            dataIndex: 'id',
            ellipsis: true,
        },
        {
            title: '实验室任务编号',
            dataIndex: 'taskdNo',
            ellipsis: true,
        },
        {
            title: '项目ID',
            dataIndex: 'projectId',
            ellipsis: true,
        },
        {
            title: '三方机构ID（实验室ID）',
            dataIndex: 'thirdPartyId',
            ellipsis: true,
        },
        {
            title: '仪器名称',
            dataIndex: 'instrument',
            ellipsis: true,
        },
        {
            title: '有效期',
            dataIndex: 'expiryDate',
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
            title: '完成日期',
            dataIndex: 'finishDate',
            ellipsis: true,
        },
        {
            title: '状态（ProjectStatusEnum）',
            dataIndex: 'status',
            ellipsis: true,
        },
        {
            title: '操作人',
            dataIndex: 'operateUserId',
            ellipsis: true,
        },
        {
            title: '操作人姓名',
            dataIndex: 'operateUserName',
            ellipsis: true,
        },
        {
            title: '操作时间',
            dataIndex: 'operateTime',
            ellipsis: true,
        },
        {
            title: '创建人',
            dataIndex: 'createUserId',
            ellipsis: true,
        },
        {
            title: '创建人姓名',
            dataIndex: 'createUserName',
            ellipsis: true,
        },
        {
            title: '创建时间',
            dataIndex: 'createTime',
            ellipsis: true,
        },
        {
            title: '更新人',
            dataIndex: 'updateUserId',
            ellipsis: true,
        },
        {
            title: '更新人姓名',
            dataIndex: 'updateUserName',
            ellipsis: true,
        },
        {
            title: '更新时间',
            dataIndex: 'updateTime',
            ellipsis: true,
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


    onMounted(queryData);

    // ---------------------------- 添加/修改 ----------------------------
    const formRef = ref();

    function showForm(data) {
        formRef.value.show(data);
    }

</script>
