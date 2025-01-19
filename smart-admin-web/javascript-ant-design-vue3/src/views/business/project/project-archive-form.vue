<!--
  * 项目归档表
  *
  * @Author:    cbh
  * @Date:      2024-05-15 13:46:16
  * @Copyright  Remote Nomad Studio
-->
<template>
    <a-modal
      :title="form.id ? '编辑' : '添加'"
      width="500px"
      :open="visibleFlag"
      @cancel="onClose"
      :maskClosable="false"
      :destroyOnClose="true"
    >
        <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }" >
            <a-row>
                    <a-form-item label="编号"  name="id">
                        <a-input-number style="width: 100%" v-model:value="form.id" placeholder="编号" />
                    </a-form-item>
                    <a-form-item label="项目ID"  name="projectId">
                        <a-input-number style="width: 100%" v-model:value="form.projectId" placeholder="项目ID" />
                    </a-form-item>
                    <a-form-item label="归档位置"  name="archivePosition">
                        <a-input style="width: 100%" v-model:value="form.archivePosition" placeholder="归档位置" />
                    </a-form-item>
                    <a-form-item label="归档日期"  name="archiveDate">
                        <a-date-picker valueFormat="YYYY-MM-DD" v-model:value="form.archiveDate" style="width: 100%" placeholder="归档日期"/>
                    </a-form-item>
                    <a-form-item label="创建人"  name="createUserId">
                        <a-input-number style="width: 100%" v-model:value="form.createUserId" placeholder="创建人" />
                    </a-form-item>
                    <a-form-item label="创建人姓名"  name="createUserName">
                        <a-input style="width: 100%" v-model:value="form.createUserName" placeholder="创建人姓名" />
                    </a-form-item>
                    <a-form-item label="创建时间"  name="createTime">
                        <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.createTime" style="width: 100%" placeholder="创建时间" />
                    </a-form-item>
                    <a-form-item label="更新人"  name="updateUserId">
                        <a-input-number style="width: 100%" v-model:value="form.updateUserId" placeholder="更新人" />
                    </a-form-item>
                    <a-form-item label="更新人姓名"  name="updateUserName">
                        <a-input style="width: 100%" v-model:value="form.updateUserName" placeholder="更新人姓名" />
                    </a-form-item>
                    <a-form-item label="更新时间"  name="updateTime">
                        <a-date-picker show-time valueFormat="YYYY-MM-DD HH:mm:ss" v-model:value="form.updateTime" style="width: 100%" placeholder="更新时间" />
                    </a-form-item>
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
    import { reactive, ref, nextTick } from 'vue';
    import _ from 'lodash';
    import { message } from 'ant-design-vue';
    import { SmartLoading } from '/@/components/framework/smart-loading';
    import { projectArchiveApi } from '/@/api/business/project/project-archive-api';
    import { smartSentry } from '/@/lib/smart-sentry';

    // ------------------------ 事件 ------------------------

    const emits = defineEmits(['reloadList']);

    // ------------------------ 显示与隐藏 ------------------------
    // 是否显示
    const visibleFlag = ref(false);

    function show(rowData) {
        Object.assign(form, formDefault);
        if (rowData && !_.isEmpty(rowData)) {
            Object.assign(form, rowData);
        }
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
        id: undefined,
        id: undefined, //编号
        projectId: undefined, //项目ID
        archivePosition: undefined, //归档位置
        archiveDate: undefined, //归档日期
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
                await projectArchiveApi.update(form);
            } else {
                await projectArchiveApi.add(form);
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
