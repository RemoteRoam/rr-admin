<!--
  * 三方机构
  *
  * @Author:    cbh
  * @Date:      2024-04-24 09:19:13
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
                <a-col :span="24">
                    <a-form-item label="公司名称"  name="name">
                        <a-input style="width: 100%" v-model:value="form.name" placeholder="公司名称" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="三方类型"  name="type">
                        <DictSelect width="100%" v-model:value="form.type" keyCode="THIRD_PARTY" placeholder="三方类型"/>
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="联系人"  name="contact">
                        <a-input style="width: 100%" v-model:value="form.contact" placeholder="联系人" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="联系人电话"  name="contactPhone">
                        <a-input style="width: 100%" v-model:value="form.contactPhone" placeholder="联系人电话" />
                    </a-form-item>
                </a-col>
                <a-col :span="24">
                    <a-form-item label="所在城市" name="provinceCityDistrict">
                        <AreaCascader type="province_city_district" style="width: 100%" v-model:value="area" placeholder="请选择所在城市" @change="changeArea" />
                    </a-form-item>
                    </a-col>
                <a-col :span="24">
                    <a-form-item label="详细地址"  name="address">
                        <a-input style="width: 100%" v-model:value="form.address" placeholder="详细地址" />
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
    import { reactive, ref, nextTick } from 'vue';
    import _ from 'lodash';
    import { message } from 'ant-design-vue';
    import { SmartLoading } from '/@/components/framework/smart-loading';
    import { thirdPartyApi } from '/@/api/business/third/third-party-api';
    import { smartSentry } from '/@/lib/smart-sentry';
    import { regular } from '/@/constants/regular-const';
    import DictSelect from '/@/components/support/dict-select/index.vue';
    import AreaCascader from '/@/components/framework/area-cascader/index.vue';

    // ------------------------ 事件 ------------------------

    const emits = defineEmits(['reloadList']);

    // ------------------------ 显示与隐藏 ------------------------
    // 是否显示
    const visibleFlag = ref(false);

    function show(rowData) {
        Object.assign(form, formDefault);
        area.value = [];
        if (rowData.id) {
            detail(rowData.id);
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

  async function detail(customerId) {
    try {
      let result = await thirdPartyApi.detail(customerId);
      let data = result.data;
      Object.assign(form, data);
      nextTick(() => {
        console.log(data.provinceName);
        // 省市区不存在，不需要赋值
        if (!data.provinceName) {
          return;
        }
        area.value = [
          {
            value: data.province,
            label: data.provinceName,
          },
          {
            value: data.city,
            label: data.cityName,
          },
          {
            value: data.district,
            label: data.districtName,
          },
        ];
        formRef.value.clearValidate();
      });
    } catch (error) {
      smartSentry.captureError(error);
    } finally {
      SmartLoading.hide();
    }
  }
    // ------------------------ 表单 ------------------------

    // 组件ref
    const formRef = ref();

    const formDefault = {
        id: undefined, //三方ID
        name: undefined, //公司名称
        type: undefined, //三方类型
        contact: undefined, //联系人
        contactPhone: undefined, //联系人电话
        province: undefined, //省份
        provinceName: undefined, //省份名称
        city: undefined, //市
        cityName: undefined, //城市名称
        district: undefined, //区县
        districtName: undefined, //区县名称
        address: undefined, //详细地址
    };

    let form = reactive({ ...formDefault });

    const rules = {
        name: [{ required: true, message: '公司名称 必填' }],
        contactPhone: [
            { pattern: regular.phone, message: '请输入正确的联系人电话', trigger: 'blur' },
        ],
    };

  // 地区
  const area = ref([]);

  function changeArea(value, selectedOptions) {
    Object.assign(form, {
      province: '',
      provinceName: '',
      city: '',
      cityName: '',
      district: '',
      districtName: '',
    });
    if (!_.isEmpty(selectedOptions)) {
      // 地区信息
      form.province = area.value[0].value;
      form.provinceName = area.value[0].label;

      if (area.value[1]) {
        form.city = area.value[1].value;
        form.cityName = area.value[1].label;
      }
      if (area.value[2]) {
        form.district = area.value[2].value;
        form.districtName = area.value[2].label;
      }
    }
  }

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
                await thirdPartyApi.update(form);
            } else {
                await thirdPartyApi.add(form);
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
