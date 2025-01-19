<!--
  * 客户表
  *
  * @Author:    cbh
  * @Date:      2024-04-23 12:10:04
  * @Copyright  Remote Nomad Studio
-->
<template>
  <a-modal :title="form.customerId ? '编辑' : '添加'" width="800px" :open="visibleFlag" @cancel="onClose"
    :maskClosable="false" :destroyOnClose="true">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">

      <a-row>
        <a-col :span="24">
          <a-form-item label="客户名称" name="customerName">
            <a-input style="width: 100%" v-model:value="form.customerName" placeholder="客户名称" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="客户级别" name="customerLevel">
            <DictSelect width="100%" v-model:value="form.customerLevel" keyCode="CUSTOMER_LEVEL" placeholder="客户级别" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人1" name="contact">
            <a-input style="width: 100%" v-model:value="form.contact" placeholder="联系人1" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人1电话" name="contactPhone">
            <a-input style="width: 100%" v-model:value="form.contactPhone" placeholder="联系人1电话" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人2" name="contactSecond">
            <a-input style="width: 100%" v-model:value="form.contactSecond" placeholder="联系人2" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人2电话" name="contactSecondPhone">
            <a-input style="width: 100%" v-model:value="form.contactSecondPhone" placeholder="联系人2电话" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人3" name="contactThird">
            <a-input style="width: 100%" v-model:value="form.contactThird" placeholder="联系人3" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="联系人3电话" name="contactThirdPhone">
            <a-input style="width: 100%" v-model:value="form.contactThirdPhone" placeholder="联系人3电话" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="所在城市" name="provinceCityDistrict">
            <AreaCascader type="province_city_district" style="width: 100%" v-model:value="area" placeholder="请选择所在城市"
              @change="changeArea" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="详细地址" name="address">
            <a-input style="width: 100%" v-model:value="form.address" placeholder="详细地址" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="工厂所在城市" name="provinceCityDistrict">
            <AreaCascader type="province_city_district" style="width: 100%" v-model:value="factoryArea"
              placeholder="请选择工厂所在城市" @change="changeFactoryArea" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="工厂详细地址" name="factoryAddress">
            <a-input style="width: 100%" v-model:value="form.factoryAddress" placeholder="工厂详细地址" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="企业规模" name="size">
            <a-input style="width: 100%" v-model:value="form.size" placeholder="企业规模" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="主营业务" name="business">
            <a-textarea style="width: 100%" v-model:value="form.business" placeholder="主营业务" />
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
import { customerApi } from '/@/api/business/customer/customer-api';
import { smartSentry } from '/@/lib/smart-sentry';
import { regular } from '/@/constants/regular-const';
import DictSelect from '/@/components/support/dict-select/index.vue';
import AreaCascader from '/@/components/framework/area-cascader/index.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['refresh']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData, type) {
  console.log(type);
  Object.assign(form, formDefault);
  area.value = [];
  factoryArea.value = [];
  form.type = type;
  if (rowData.customerId) {
    detail(rowData.customerId);
  }
  visibleFlag.value = true;

}

function onClose() {
  Object.assign(form, formDefault);
  visibleFlag.value = false;
}

async function detail(customerId) {
  try {
    let result = await customerApi.detail(customerId);
    let data = result.data;
    Object.assign(form, data);
    nextTick(() => {

      if (form.customerLevel && form.customerLevel.length > 0) {
        form.customerLevel = form.customerLevel[0].valueCode;
      }
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
      // 省市区不存在，不需要赋值
      if (!data.factoryProvinceName) {
        return;
      }
      factoryArea.value = [
        {
          value: data.factoryProvince,
          label: data.factoryProvinceName,
        },
        {
          value: data.factoryCity,
          label: data.factoryCityName,
        },
        {
          value: data.factoryDistrict,
          label: data.factoryDistrictName,
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
  customerId: undefined,
  customerName: undefined, //客户名称
  type: undefined, //客户类型
  customerLevel: 'LEVEL_C', //客户级别
  contact: undefined, //联系人1
  contactPhone: undefined, //联系人1电话
  contactSecond: undefined, //联系人2
  contactSecondPhone: undefined, //联系人2电话
  contactThird: undefined, //联系人3
  contactThirdPhone: undefined, //联系人3电话
  province: undefined, //省份
  provinceName: undefined, //省份名称
  city: undefined, //市
  cityName: undefined, //城市名称
  district: undefined, //区县
  districtName: undefined, //区县名称
  address: undefined, //详细地址
  factoryProvince: undefined, //工厂省份
  factoryProvinceName: undefined, //工厂省份名称
  factoryCity: undefined, //工厂市
  factoryCityName: undefined, //工厂城市名称
  factoryDistrict: undefined, //工厂区县
  factoryDistrictName: undefined, //工厂区县名称
  factoryAddress: undefined, //工厂详细地址
  size: undefined, //企业规模
  business: undefined, //主营业务
};

let form = reactive({ ...formDefault });

const rules = {
  customerName: [{ required: true, message: '客户名称 必填' }],
  contactPhone: [
    { pattern: regular.phone, message: '请输入正确的联系人电话', trigger: 'blur' },
  ],
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
    console.log(form);
    if (form.customerId) {
      await customerApi.update(form);
    } else {
      await customerApi.add(form);
    }
    message.success('操作成功');
    emits('refresh');
    onClose();
  } catch (err) {
    smartSentry.captureError(err);
  } finally {
    SmartLoading.hide();
  }
}

// 地区
const area = ref([]);

// 工厂地区
const factoryArea = ref([]);


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

function changeFactoryArea(value, selectedOptions) {
  Object.assign(form, {
    factoryProvince: '',
    factoryProvinceName: '',
    factoryCity: '',
    factoryCityName: '',
    factoryDistrict: '',
    factoryDistrictName: '',
  });
  if (!_.isEmpty(selectedOptions)) {
    // 地区信息
    form.factoryProvince = factoryArea.value[0].value;
    form.factoryProvinceName = factoryArea.value[0].label;

    if (factoryArea.value[1]) {
      form.factoryCity = factoryArea.value[1].value;
      form.factoryCityName = factoryArea.value[1].label;
    }
    if (factoryArea.value[2]) {
      form.factoryDistrict = factoryArea.value[2].value;
      form.factoryDistrictName = factoryArea.value[2].label;
    }
  }
}

defineExpose({
  show,
});
</script>
