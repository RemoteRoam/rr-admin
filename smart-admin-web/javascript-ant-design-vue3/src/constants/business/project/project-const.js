/**
 * 体系认证表 枚举
 *
 * @Author:    cbh
 * @Date:      2024-04-25 14:53:11
 * @Copyright  Remote Nomad Studio
 */


export const PROJECT_TYPE_PRODUCT_ENUM = {
  CCC: {
    value: 11,
    desc: 'CCC自我声明',
  },
  PRODUCT_CERTIFICATION: {
    value: 12,
    desc: '产品认证',
  },
  LAB_PROJECT: {
    value: 21,
    desc: '实验项目',
  },
  PRODUCT_SUPERVISION: {
    value: 31,
    desc: '产品监督',
  },
};

export const PROJECT_TYPE_LAB_ENUM = {
  CCC: {
    value: 11,
    desc: 'CCC自我声明',
  },
  PRODUCT_CERTIFICATION: {
    value: 12,
    desc: '产品认证',
  },
  LAB_PROJECT: {
    value: 21,
    desc: '实验项目',
  },
};

export const PROJECT_CATEGORY_ENUM = {
  INIT: {
    value: 1,
    desc: '初始',
  },
  CHANGE: {
    value: 2,
    desc: '变更',
  },
  ADD: {
    value: 3,
    desc: '增项',
  },
  KA: {
    value: 4,
    desc: 'KA',
  }
};

export const LAB_CATEGORY_ENUM = {
  INIT: {
    value: 1,
    desc: '委托',
  },
  CHANGE: {
    value: 2,
    desc: '型式',
  },
  ADD: {
    value: 3,
    desc: '监督抽样',
  },
  KY: {
    value: 4,
    desc: 'KY',
  }
};

export const PROJECT_TYPE_SUPERVISION_ENUM = {
  PRODUCT_SUPERVISION: {
    value: 31,
    desc: '产品监督',
  },
};


export default {
  PROJECT_TYPE_PRODUCT_ENUM,
  PROJECT_TYPE_LAB_ENUM,
  PROJECT_TYPE_SUPERVISION_ENUM,
  PROJECT_CATEGORY_ENUM,
  LAB_CATEGORY_ENUM
};