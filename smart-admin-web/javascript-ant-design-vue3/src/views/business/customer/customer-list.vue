<!--
  * 客户表
  *
  * @Author:    cbh
  * @Date:      2024-04-23 12:10:04
  * @Copyright  Remote Nomad Studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="客户名称" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.customerName" placeholder="客户名称" />
            </a-form-item>
            <a-form-item label="客户级别" class="smart-query-form-item">
                <DictSelect key-code="CUSTOMER_LEVEL" v-model:value="queryForm.customerLevel" width="150px" />
            </a-form-item>
            <a-form-item label="联系人" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.contact" placeholder="联系人" />
            </a-form-item>
            <a-form-item label="联系人电话" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.contactPhone" placeholder="联系人电话" />
            </a-form-item>
        </a-row>
        <a-row class="smart-query-form-row">
            <a-form-item class="smart-query-form-item" label="所在城市">
                <AreaCascader type="province_city_district" style="width: 250px" v-model:value="area" placeholder="请选择所在城市" @change="changeArea" />
            </a-form-item>
            <a-form-item class="smart-query-form-item" label="工厂所在城市">
                <AreaCascader type="province_city_district" style="width: 250px" v-model:value="factoryArea" placeholder="请选择所在城市" @change="changeFactoryArea" />
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
                <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <DeleteOutlined />
                    </template>
                    批量删除
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
                rowKey="customerId"
                bordered
                :loading="tableLoading"
                :pagination="false"
                :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }"
        >
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="showForm(record)" type="link">编辑</a-button>
                        <a-button @click="onDelete(record)" danger type="link">删除</a-button>
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

        <CustomerForm  ref="formRef" @reloadList="queryData"/>

    </a-card>
</template>
<script setup>
    import { reactive, ref, onMounted } from 'vue';
    import { message, Modal } from 'ant-design-vue';
    import { SmartLoading } from '/@/components/framework/smart-loading';
    import { customerApi } from '/@/api/business/customer/customer-api';
    import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
    import { smartSentry } from '/@/lib/smart-sentry';
    import _ from 'lodash';
    import TableOperator from '/@/components/support/table-operator/index.vue';
    import CustomerForm from './customer-form.vue';
    import DictSelect from '/@/components/support/dict-select/index.vue';
    import AreaCascader from '/@/components/framework/area-cascader/index.vue';
    // ---------------------------- 表格列 ----------------------------

    const columns = ref([
        {
            title: '客户名称',
            dataIndex: 'customerName',
            ellipsis: true,
        },
        {
            title: '客户级别',
            dataIndex: 'customerLevel',
            ellipsis: true,
        },
        {
            title: '联系人',
            dataIndex: 'contact',
            ellipsis: true,
        },
        {
            title: '联系人电话',
            dataIndex: 'contactPhone',
            ellipsis: true,
        },
        {
            title: '省份名称',
            dataIndex: 'provinceName',
            ellipsis: true,
        },
        {
            title: '城市名称',
            dataIndex: 'cityName',
            ellipsis: true,
        },
        {
            title: '区县名称',
            dataIndex: 'districtName',
            ellipsis: true,
        },
        {
            title: '工厂省份名称',
            dataIndex: 'factoryProvinceName',
            ellipsis: true,
        },
        {
            title: '工厂城市名称',
            dataIndex: 'factoryCityName',
            ellipsis: true,
        },
        {
            title: '工厂区县名称',
            dataIndex: 'factoryDistrictName',
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
        customerName: undefined, //客户名称
        customerLevel: undefined, //客户级别
        contact: undefined, //联系人
        contactPhone: undefined, //联系人电话
        province: undefined, //省份
        city: undefined, //市
        district: undefined, //区县
        factoryProvince: undefined, //工厂省份
        factoryCity: undefined, //工厂市
        factoryDistrict: undefined, //工厂区县
        pageNum: 1,
        pageSize: 10,
    };

    const area = ref([]);
    const factoryArea = ref([]);
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

  function changeArea(value, selectedOptions) {
    Object.assign(queryForm, {
      province: '',
      city: '',
      district: '',
    });
    if (!_.isEmpty(selectedOptions)) {
      // 地区信息
      queryForm.province = area.value[0].value;

      if (area.value[1]) {
        queryForm.city = area.value[1].value;
      }
      if (area.value[2]) {
        queryForm.district = area.value[2].value;
      }
    }
  }

  function changeFactoryArea(value, selectedOptions) {
    Object.assign(queryForm, {
      factoryProvince: '',
      factoryCity: '',
      factoryDistrict: '',
    });
    if (!_.isEmpty(selectedOptions)) {
      // 地区信息
      queryForm.factoryProvince = factoryArea.value[0].value;
      queryForm.factoryCity = factoryArea.value[1].value;
      queryForm.factoryDistrict = factoryArea.value[2].value;
    }
  }

    // 查询数据
    async function queryData() {
        tableLoading.value = true;
        try {
            let queryResult = await customerApi.queryPage(queryForm);
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

    // ---------------------------- 单个删除 ----------------------------
    //确认删除
    function onDelete(data){
        Modal.confirm({
            title: '提示',
            content: '确定要删除选吗?',
            okText: '删除',
            okType: 'danger',
            onOk() {
                requestDelete(data);
            },
            cancelText: '取消',
            onCancel() {},
        });
    }

    //请求删除
    async function requestDelete(data){
        SmartLoading.show();
        try {
            let deleteForm = {
                goodsIdList: selectedRowKeyList.value,
            };
            await customerApi.delete(data.customerId);
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
            onCancel() {},
        });
    }

    //请求批量删除
    async function requestBatchDelete() {
        try {
            SmartLoading.show();
            await customerApi.batchDelete(selectedRowKeyList.value);
            message.success('删除成功');
            queryData();
        } catch (e) {
            smartSentry.captureError(e);
        } finally {
            SmartLoading.hide();
        }
    }
</script>