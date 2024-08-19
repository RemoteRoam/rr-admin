<!--
  * 项目试验室任务表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:19:26
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-col :span="16">
                    <a-form-item label="试验室" name="thirdPartyId">
                        <ThirdPartySelect width="95%" v-model:value="form.thirdPartyId" placeholder="请选择试验室"
                            type="THIRD_1" />
                    </a-form-item>
                </a-col>
            </a-row>

            <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="handleAdd">添加产品</a-button>
            <a-table bordered :data-source="dataSource" :columns="columns">
                <template #bodyCell="{ column, text, record }">
                    <template v-if="column.dataIndex === 'productName'">
                        <a-input v-model:value="record.productName" />
                    </template>
                    <template v-if="column.dataIndex === 'productModel'">
                        <a-textarea v-model:value="record.productModel" />
                    </template>
                    <template v-else-if="column.dataIndex === 'operation'">
                        <a-popconfirm v-if="dataSource.length" title="确认删除?" @confirm="onDelete(record.key)">
                            <a>删除</a>
                        </a-popconfirm>
                    </template>
                </template>
            </a-table>
        </a-form>

        <template #footer>
            <a-space>
                <a-button @click="onClose">取消</a-button>
                <a-button type="primary" @click="onSubmit">保存</a-button>
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
import { projectLabApi } from '/@/api/business/project/project-lab-api';
import { smartSentry } from '/@/lib/smart-sentry';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
    Object.assign(form, formDefault);
    form.projectId = rowData.id;
    form.projectType = rowData.projectType;
    visibleFlag.value = true;
    nextTick(() => {
        formRef.value.clearValidate();
    });
}

function onClose() {
    Object.assign(form, formDefault);
    dataSource.value = [{
        key: '1',
        productName: '',
        productModel: '',
    }];
    visibleFlag.value = false;
}

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const formDefault = {
    projectId: undefined, //项目ID
    projectType: undefined, //项目类型
    thirdPartyId: undefined, //三方机构ID（试验室ID）
    products: [], //产品列表

};

let form = reactive({ ...formDefault });

const rules = {
};

// 点击确定，验证表单
async function onSubmit() {
    try {
        await formRef.value.validateFields();
        form.products = cloneDeep(dataSource.value);
        submit();
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
}

// 新建、编辑API
async function submit() {
    SmartLoading.show();
    try {
        if (form.id) {
            await projectLabApi.update(form);
        } else {
            await projectLabApi.add(form);
        }
        message.success('操作成功');
        emits('reloadList');
        onClose();
    } catch (err) {
        smartSentry.captureError(err);
    } finally {
        SmartLoading.hide();
    }
}

const columns = [
    {
        title: '产品名称',
        dataIndex: 'productName',
        width: '30%',
    },
    {
        title: '产品型号',
        dataIndex: 'productModel',
    },
    {
        title: '操作',
        dataIndex: 'operation',
    },
];
const dataSource = ref([{
    key: '1',
    productName: '',
    productModel: '',
}]);
const count = computed(() => dataSource.value.length + 1);

const onDelete = key => {
    dataSource.value = dataSource.value.filter(item => item.key !== key);
};
const handleAdd = () => {
    const newData = {
        key: `${count.value}`,
        productName: '',
        productModel: '',
    };
    dataSource.value.push(newData);
};

defineExpose({
    show,
});
</script>
