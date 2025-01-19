/**
 * 体系认证表 枚举
 *
 * @Author:    cbh
 * @Date:      2024-04-25 14:53:11
 * @Copyright  Remote Nomad Studio
 */


export const PROJECT_TYPE_SYSTEM_ENUM = {
  INITIALIZATION: {
    value: 41,
    desc: '体系初始',
  },
  SUPERVISION1: {
    value: 421,
    desc: '体系监督一',
  },
  SUPERVISION2: {
    value: 422,
    desc: '体系监督二',
  },
  RE_CERTIFICATION: {
    value: 43,
    desc: '再认证',
  },
  EXPANSION: {
    value: 44,
    desc: '扩项',
  },
  PRODUCT: {
    value: 45,
    desc: '产品认证',
  },
};

export default {
  PROJECT_TYPE_SYSTEM_ENUM,
};