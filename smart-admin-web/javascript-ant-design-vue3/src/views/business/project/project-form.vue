<!--
  * 项目表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:54:05
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal :title="form.id ? '编辑' : '添加'" width="60%" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
        :destroyOnClose="true">
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ style: { width: '150px' } }">
            <a-row>
                <a-col :span="8">
                    <a-form-item label="项目编号" name="projectNo">
                        <a-input style="width: 95%" v-model:value="form.projectNo" placeholder="项目编号" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="项目类型" name="projectType">
                        <SmartEnumSelect width="95%" v-model:value="form.projectType"
                            enumName="PROJECT_TYPE_PRODUCT_ENUM" placeholder="项目类型" disabled />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="项目分类" name="category">
                        <SmartEnumSelect width="95%" v-model:value="form.category" :enumName="enumName"
                            placeholder="项目分类" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="客户" name="customerId">
                        <CustomerSelect width="95%" v-model:value="form.customerId" placeholder="请选择客户" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="来源分类" name="sourceType">
                        <SmartEnumSelect width="95%" v-model:value="form.sourceType" enumName="SOURCE_TYPE_ENUM"
                            @change="onChangeSourceType" placeholder="来源分类" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="来源" name="sourceId">
                        <template v-if="form.sourceType === 1">
                            <ThirdPartySelect width="95%" v-model:value="form.sourceId" placeholder="请选择咨询机构"
                                type="THIRD_3" />
                        </template>
                        <template v-else-if="form.sourceType === 2">
                            <EmployeeSelect width="95%" v-model:value="form.sourceId" placeholder="请选择内部员工" />
                        </template>
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="合同号" name="contractNo">
                        <a-input style="width: 95%" v-model:value="form.contractNo" placeholder="合同号" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="合同日" name="contractDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.contractDate" style="width: 95%"
                            placeholder="合同日" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="合同金额" name="contractAmount">
                        <a-input-number style="width: 95%" v-model:value="form.contractAmount" placeholder="合同金额" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="客户预期日期" name="expectedDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.expectedDate" style="width: 95%"
                            placeholder="客户预期日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="16">
                    <a-form-item label="备注" name="remark">
                        <a-input style="width: 95%" v-model:value="form.remark" placeholder="备注" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="首款金额" name="firstPaymentAmount">
                        <a-input-number style="width: 95%" v-model:value="form.firstPaymentAmount" placeholder="首款金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="首款收款日期" name="firstPaymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.firstPaymentDate" style="width: 95%"
                            placeholder="首款收款日期" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="审核任务时间" name="auditTaskDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.auditTaskDate" style="width: 95%"
                            placeholder="审核任务时间" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="审核老师" name="auditTeacher">
                        <a-input style="width: 95%" v-model:value="form.auditTeacher" placeholder="审核老师" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="咨询老师" name="consultationTeacher">
                        <a-input style="width: 95%" v-model:value="form.consultationTeacher" placeholder="咨询老师" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="审核日期" name="auditDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.auditDate" style="width: 95%"
                            placeholder="审核日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="整改内容" name="rectificationContent">
                        <a-input style="width: 95%" v-model:value="form.rectificationContent" placeholder="整改内容" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="整改日期" name="rectificationDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.rectificationDate"
                            style="width: 95%" placeholder="整改日期" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="尾款金额" name="finalPaymentAmount">
                        <a-input-number style="width: 95%" v-model:value="form.finalPaymentAmount" placeholder="尾款金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="尾款收款日期" name="finalPaymentDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.finalPaymentDate" style="width: 95%"
                            placeholder="尾款收款日期" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="8">
                    <a-form-item label="开票日期" name="invoiceDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.invoiceDate" style="width: 95%"
                            placeholder="开票日期" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="发票金额" name="invoiceAmount">
                        <a-input-number style="width: 95%" v-model:value="form.invoiceAmount" placeholder="发票金额" />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="发票号" name="invoiceNumber">
                        <a-input style="width: 95%" v-model:value="form.invoiceNumber" placeholder="发票号" />
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

function show(id) {
    Object.assign(form, formDefault);
    detail(id);
    visibleFlag.value = true;
}

async function detail(id) {
    try {
        let result = await projectApi.detail(id);
        let data = result.data;
        Object.assign(form, data);
        projectTypeGlobal.value = data.projectType;
        nextTick(() => {
            formRef.value.clearValidate();
        });
    } catch (error) {
        smartSentry.captureError(error);
    } finally {
        SmartLoading.hide();
    }
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
    id: undefined, //编号
    projectNo: undefined, //项目编号
    projectType: undefined, //项目类型
    category: undefined, //项目分类
    customerId: undefined, //客户ID
    sourceType: undefined, //来源分类
    sourceId: undefined, //来源ID
    contractNo: undefined, //合同号
    contractDate: undefined, //合同日
    contractAmount: undefined, //合同金额
    expectedDate: undefined, //客户预期日期
    remark: undefined, //备注
    firstPaymentAmount: undefined, //首款金额
    firstPaymentDate: undefined, //首款收款日期
    auditTaskDate: undefined, //审核任务时间
    auditTeacher: undefined, //审核老师
    consultationTeacher: undefined, //咨询老师
    auditDate: undefined, //审核日期
    rectificationContent: undefined, //整改内容
    rectificationDate: undefined, //整改日期
    finalPaymentAmount: undefined, //尾款金额
    finalPaymentDate: undefined, //尾款收款日期
    invoiceDate: undefined, //开票日期
    invoiceAmount: undefined, //发票金额
    invoiceNumber: undefined, //发票号
    status: undefined, //状态
    deletedFlag: undefined, //删除状态
    createUserId: undefined, //创建人
    createUserName: undefined, //创建人姓名
    createTime: undefined, //创建时间
    updateUserId: undefined, //更新人
    updateUserName: undefined, //更新人姓名
    updateTime: undefined, //更新时间
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
