<!--
  * 体系认证表
  *
  * @Author:    cbh
  * @Date:      2024-04-25 14:53:11
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal title="跳过" width="600px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <a-col :span="24">
                    <a-form-item label="跳过原因" name="passReason">
                        <a-text-area v-model:value="form.passReason" style="width: 100%" placeholder="跳过原因" />
                    </a-form-item>
                </a-col>
            </a-row>

        </a-form>

        <template #footer>
            <a-space>
                <a-button type="primary" @click="onSubmit">保存</a-button>
                <a-button @click="onClose">取消</a-button>
            </a-space>
        </template>
    </a-modal>
</template>
<script setup>
import { reactive, ref, nextTick, } from 'vue';
import _ from 'lodash';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { systemCertificationApi } from '/@/api/business/project/system-certification-api';
import { smartSentry } from '/@/lib/smart-sentry';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['closeForm']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
    console.log('rowData', rowData);
    // Object.assign(form, formDefault);
    // if (rowData && !_.isEmpty(rowData)) {
    //     Object.assign(form, rowData);
    // }
    // visibleFlag.value = true;
    // nextTick(() => {
    //     formRef.value.clearValidate();
    // });
};

// function onClose() {
//     Object.assign(form, formDefault);
//     visibleFlag.value = false;
// };

// // ------------------------ 表单 ------------------------

// // 组件ref
// const formRef = ref();

// const formDefault = {
//     id: undefined, //项目ID
//     projectType: undefined, //项目类型
//     nodeId: 1, //节点ID
//     status: undefined, //节点状态
//     passReason: undefined, //跳过原因
//     firstPaymentDate: undefined, //首款日期
//     firstPaymentAmount: undefined, //首款金额
// };

// let form = reactive({ ...formDefault });

// const rules = {
//     passReason: [{ required: true, message: '编号 必填' }],
// };

// // 点击确定，验证表单
// async function onSubmit() {
//     try {
//         await formRef.value.validateFields();
//         save(3);
//     } catch (err) {
//         message.error('参数验证错误，请仔细填写表单数据!');
//     }
// };

// // 新建、编辑API
// async function save(status) {
//     SmartLoading.show();
//     form.status = status;
//     try {
//         console.log('form', form);
//         if (form.id) {
//             await systemCertificationApi.update(form);
//         } else {
//             // await systemCertificationApi.add(form);
//         }
//         message.success('操作成功');
//         emits('closeForm');
//         onClose();
//     } catch (err) {
//         smartSentry.captureError(err);
//     } finally {
//         SmartLoading.hide();
//     }
// }

defineExpose({
    show,
});
</script>
