<!--
  * 项目表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:54:05
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
            <a-row>
                <!-- <a-col :span="24">
                    <a-form-item label="项目类型" name="projectType">
                        <SmartEnumSelect width="100%" v-model:value="form.projectType"
                            enumName="PROJECT_TYPE_PRODUCT_ENUM" placeholder="项目类型" />
                    </a-form-item>
                </a-col> -->
                <a-col :span="24" v-if="form.projectType != 31">
                    <a-form-item label="项目分类" name="category">
                        <SmartEnumSelect width="100%" v-model:value="form.category" :enumName="enumName"
                            placeholder="项目分类" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="客户ID" name="customerId">
                        <CustomerSelect width="100%" v-model:value="form.customerId" placeholder="请选择客户" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="来源分类" name="sourceType">
                        <SmartEnumSelect width="100%" v-model:value="form.sourceType" enumName="SOURCE_TYPE_ENUM"
                            @change="onChangeSourceType" placeholder="来源分类" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="来源" name="sourceId">

                        <template v-if="form.sourceType === 1">
                            <ThirdPartySelect width="100%" v-model:value="form.sourceId" placeholder="请选择咨询机构"
                                type="THIRD_3" />
                        </template>
                        <template v-else-if="form.sourceType === 2">
                            <EmployeeSelect width="100%" v-model:value="form.sourceId" placeholder="请选择内部员工" />
                        </template>
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="上年度审核日期" name="lastYearAuditDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.lastYearAuditDate"
                            style="width: 100%" placeholder="上年度审核日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="审核日期" name="auditDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.auditDate" style="width: 95%"
                            placeholder="审核日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="咨询老师" name="consultationTeacher">
                        <a-input style="width: 95%" v-model:value="form.consultationTeacher" placeholder="咨询老师" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="证书到期日" name="certificateExpiryDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.certificateExpiryDate"
                            style="width: 100%" placeholder="证书到期日" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="备注" name="remark">
                        <a-textarea style="width: 100%" v-model:value="form.remark" placeholder="备注" />
                    </a-form-item>
                </a-col>
            </a-row>

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
import _ from 'lodash';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { projectApi } from '/@/api/business/project/project-api';
import { smartSentry } from '/@/lib/smart-sentry';
import BooleanSelect from '/@/components/framework/boolean-select/index.vue';
import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
import CustomerSelect from '/@/components/business/project/customer-select/index.vue';
import ThirdPartySelect from '/@/components/business/project/third-party-select/index.vue';
import EmployeeSelect from '/@/components/system/employee-select/index.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);
// -------------------  监听数据变化 -------------------
function onChangeSourceType(value) {
    form.sourceId = null;
}

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(projectType) {
    Object.assign(form, formDefault);
    form.projectType = projectType;
    projectTypeGlobal.value = projectType;
    visibleFlag.value = true;
    nextTick(() => {
        formRef.value.clearValidate();
    });
}

function onClose() {
    Object.assign(form, formDefault);
    visibleFlag.value = false;
}

const projectTypeGlobal = ref(null);

const enumName = computed(() => {
    return projectTypeGlobal.value == 21 ? 'LAB_CATEGORY_ENUM' : 'PROJECT_CATEGORY_ENUM';
});

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const formDefault = {
    projectType: undefined, //项目类型
    category: undefined, //项目分类
    customerId: undefined, //客户ID
    sourceType: undefined, //来源分类
    sourceId: undefined, //来源ID
    consultationTeacher: undefined, //咨询老师
    auditDate: undefined, //审核日期
    certificateExpiryDate: undefined, //证书到期日
    lastYearAuditDate: undefined, //上年度审核日期
    remark: undefined, //备注
};

let form = reactive({ ...formDefault });

const rules = {
};

// 点击确定，验证表单
async function onSubmit() {
    try {
        await formRef.value.validateFields();
        save();
    } catch (err) {
        message.error('参数验证错误，请仔细填写表单数据!');
    }
}

// 新建、编辑API
async function save() {
    SmartLoading.show();
    try {
        if (form.id) {
            await projectApi.update(form);
        } else {
            await projectApi.add(form);
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

defineExpose({
    show,
});
</script>
