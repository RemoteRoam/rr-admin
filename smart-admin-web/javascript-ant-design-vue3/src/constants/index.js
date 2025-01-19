/*
 * 所有常量入口
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 19:58:28
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import menu from './system/menu-const';
import goods from './business/erp/goods-const';
import category from './business/erp/category-const';
import { LOGIN_DEVICE_ENUM } from './system/login-device-const';
import { FLAG_NUMBER_ENUM, GENDER_ENUM, USER_TYPE_ENUM, SOURCE_TYPE_ENUM, PROJECT_STATUS_ENUM, SALES_TYPE_ENUM, SALES_COMPANY_ENUM, PAY_PARTY_ENUM, NODE_STATUS_ENUM } from './common-const';
import { LAYOUT_ENUM } from './layout-const';
import file from './support/file-const';
import notice from './business/oa/notice-const';
import loginLog from './support/login-log-const';
import enterprise from './business/oa/enterprise-const';
import codeGeneratorConst from './support/code-generator-const';
import changeLogConst from './support/change-log-const';
import systemCertificationConst from './business/project/system-certification-const';
import projectConst from './business/project/project-const';

export default {
  FLAG_NUMBER_ENUM,
  LOGIN_DEVICE_ENUM,
  GENDER_ENUM,
  USER_TYPE_ENUM,
  LAYOUT_ENUM,
  SOURCE_TYPE_ENUM,
  PROJECT_STATUS_ENUM,
  SALES_TYPE_ENUM,
  SALES_COMPANY_ENUM,
  PAY_PARTY_ENUM,
  NODE_STATUS_ENUM,
  ...loginLog,
  ...menu,
  ...goods,
  ...category,
  ...file,
  ...notice,
  ...enterprise,
  ...codeGeneratorConst,
  ...changeLogConst,
  ...systemCertificationConst,
  ...projectConst,
};
