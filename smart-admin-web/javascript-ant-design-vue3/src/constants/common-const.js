/*
 * 通用常量
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 19:57:29
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */

export const PAGE_SIZE = 10;

export const PAGE_SIZE_OPTIONS = ['5', '10', '15', '20', '30', '40', '50', '75', '100', '150', '200', '300', '500'];

//登录页面名字
export const PAGE_PATH_LOGIN = '/login';

//404页面名字
export const PAGE_PATH_404 = '/404';

//外部页面名字
export const PAGE_PATH_PROGRESS = '/project/progress';

export const showTableTotal = function (total) {
  return `共${total}条`;
};

export const FLAG_NUMBER_ENUM = {
  TRUE: {
    value: 1,
    desc: '是',
  },
  FALSE: {
    value: 0,
    desc: '否',
  },
};

export const GENDER_ENUM = {
  UNKNOWN: {
    value: 0,
    desc: '未知',
  },
  MAN: {
    value: 1,
    desc: '男',
  },
  WOMAN: {
    value: 2,
    desc: '女',
  },
};

export const USER_TYPE_ENUM = {
  ADMIN_EMPLOYEE: {
    value: 1,
    desc: '员工',
  },

};

export const DATA_TYPE_ENUM = {
  NORMAL: {
    value: 1,
    desc: '普通',
  },
  ENCRYPT: {
    value: 10,
    desc: '加密',
  },

};

export const SOURCE_TYPE_ENUM = {
  EXTERNAL: {
    value: 1,
    desc: '外部',
  },
  INTERNAL: {
    value: 2,
    desc: '内部',
  },

};

export const PAY_PARTY_ENUM = {
  EXTERNAL: {
    value: 1,
    desc: '客户',
  },
  INTERNAL: {
    value: 2,
    desc: '我方',
  },

};


export const NODE_STATUS_ENUM = {
  INIT: {
    value: 0,
    desc: '未开始',
  },
  DOING: {
    value: 1,
    desc: '处理中',
  },
  OK: {
    value: 2,
    desc: '完成',
  },
  JUMP: {
    value: 3,
    desc: '跳过',
  },

};


export const PROJECT_STATUS_ENUM = {
  // INIT: {
  //   value: 0,
  //   desc: '未开始',
  // },
  DOING: {
    value: 1,
    desc: '处理中',
  },
  DONE: {
    value: 2,
    desc: '已完成',
  },

};


export const SALES_TYPE_ENUM = {
  INVENTORY: {
    value: 0,
    desc: '库存',
  },
  MANUFACTURER: {
    value: 1,
    desc: '厂家',
  },

};


export const SALES_COMPANY_ENUM = {
  TCCTXNY: {
    value: 1,
    desc: '天传诚通新能源科技（天津）有限公司',
  },
  TCCT: {
    value: 2,
    desc: '天津天传诚通科技发展有限公司',
  },
};