/*
 * 数据变动记录
 *
 * @Author:    YY Studio
 * @Date:      2022-09-03 22:09:10
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */

// 业务类型
export const DATA_TRACER_TYPE_ENUM = {
  GOODS: {
    value: 1,
    desc: '商品',
  },
  OA_NOTICE: {
    value: 2,
    desc: 'OA-通知公告',
  },
  OA_ENTERPRISE: {
    value: 3,
    desc: 'OA-企业信息',
  },
  SYSTEM_CERTIFICATION: {
    value: 4,
    desc: '体系认证',
  },
  MEASUREMENT: {
    value: 5,
    desc: '仪器计量',
  },
  MEASUREMENT_TASK: {
    value: 6,
    desc: '仪器计量试验室任务',
  },
  SALES: {
    value: 61,
    desc: '销售',
  },
};

export default {
  DATA_TRACER_TYPE_ENUM,
};
