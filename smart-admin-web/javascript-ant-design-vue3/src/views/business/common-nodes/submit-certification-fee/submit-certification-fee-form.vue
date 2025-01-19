<template>
    <a-modal title="交认证费" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false" :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }">

        </a-table>
        <!---------- 表格 end ----------->
        <a-form class="smart-margin-top10" ref="formRef" :model="form" :rules="rules" :label-col="{ span: 8 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="是否付款" name="isPaid">
                        <!-- <a-checkbox :true-value="1" :false-value="0" v-model:checked="form.isPaid">是否付款</a-checkbox> -->
                        <!-- <a-checkbox :value="formattedValue" @change="onChange" /> -->
                        <a-checkbox v-model:checked="form.isPaid" @change="onChange" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row v-if="form.isPaid">
                <a-col :span="24">
                    <a-form-item label="付款方" name="payParty">
                        <SmartEnumSelect width="95%" v-model:value="form.payParty" enumName="PAY_PARTY_ENUM"
                            placeholder="付款方" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row v-if="form.isPaid">
                <a-col :span="24">
                    <a-form-item label="认证费付款日期" name="payDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.payDate" style="width: 95%"
                            placeholder="认证费付款日期" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-item label="认证费备注" name="payRemark">
                        <a-textarea style="width: 95%" v-model:value="form.payRemark" placeholder="认证费备注" />
                    </a-form-item>
                </a-col>
            </a-row>

        </a-form>

        <template #footer>
            <a-space>
                <a-button type="primary" @click="onJump">跳过</a-button>
                <a-button type="primary" @click="onSubmit">保存</a-button>
                <a-button @click="onClose">取消</a-button>
            </a-space>
        </template>
    </a-modal>
    <!-- <JumpNodeForm ref="jumpNodeRef" @closeForm="onClose" /> -->
</template>
<script setup>
import { reactive, ref, nextTick, h } from 'vue';
import { computed } from 'vue';
import _ from 'lodash';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectApi } from '/@/api/business/project/project-api';
import { smartSentry } from '/@/lib/smart-sentry';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import NODE_CONST from '/@/constants/business/project/node-const';
// import { JumpNodeForm } from '../jump-node/jump-node-form.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

const jumpNodeRef = ref();

function show(rowData, projectNodeId) {
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
        Object.assign(form, rowData);
        form.projectNodeId = projectNodeId;
    }
    queryData();
    visibleFlag.value = true;
    nextTick(() => {
        formRef.value.clearValidate();
    });
}

function onClose() {
    Object.assign(form, formDefault);
    visibleFlag.value = false;
}

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const formDefault = {
    id: undefined, //项目ID
    projectType: undefined, //项目类型
    projectNodeId: undefined, //项目节点ID
    nodeId: NODE_CONST.submit_certification_fee, //节点ID
    nodeStatus: undefined, //节点状态
    passReason: undefined, //跳过原因
    isPaid: 0, //是否付款
    payParty: undefined, //付款方
    payDate: undefined, //认证费付款日期
    payRemark: undefined, //认证费备注
    productIdList: [], //产品ID列表
};

let form = reactive({ ...formDefault });

const rules = {
    // id: [{ required: true, message: '编号 必填' }],
};

// 点击确定，验证表单
async function onSubmit() {

    if (selectedRowKeyList.value.length === 0) {
        message.error('请选择产品');
        return;
    }
    try {
        await formRef.value.validateFields();
        save(2);
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
};

const formattedValue = computed(() => {
    return form.isPaid ? 1 : 0;
});
const onChange = (event) => {
    if (!event.target.checked) {
        form.payParty = undefined;
        form.payDate = undefined;
    }
};

// 点击跳过，弹出Modal.confirm框，输入跳过原因，值赋给form.passReason，点击确认后调用save方法，参数nodeStatus传3

function onJump() {
    // 用另一种方式处理，暂时有问题，后续再调查
    // jumpNodeRef.value.show(form);

    Modal.confirm({
        title: '跳过',
        content:
            h('textarea', {
                style: { marginTop: '20px', width: '80%' },
                placeholder: '请输入跳过原因',
                'onUpdate:modelValue': value => {
                    form.passReason = value;
                },
                value: form.passReason,
                onInput: event => {
                    form.passReason = event.target.value;
                }
            }),
        okText: '确认',
        cancelText: '取消',
        onOk() {
            save(3);
        },
        onCancel() {
            console.log('Cancel');
        },
    });
}



// 新建、编辑API
async function save(nodeStatus) {
    SmartLoading.show();
    form.nodeStatus = nodeStatus;
    form.productIdList = selectedRowKeyList;
    form.isPaid = form.isPaid ? 1 : 0;
    try {
        await projectApi.update(form);
        message.success('操作成功');
        emits('reloadList');
        onClose();
    } catch (err) {
        smartSentry.captureError(err);
    } finally {
        SmartLoading.hide();
    }
}

// ---------------------------- 表格列 ----------------------------

const columns = ref([
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
]);
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);


// 选择表格行
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
}

// 查询数据
async function queryData() {
    tableLoading.value = true;
    try {
        let queryResult = await projectApi.getPendingProduct(form.id, form.nodeId);
        tableData.value = queryResult.data;
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
