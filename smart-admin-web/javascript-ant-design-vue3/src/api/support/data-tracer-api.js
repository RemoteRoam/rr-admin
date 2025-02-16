/*
 * 数据变动
 *
 * @Author:    卓大
 * @Date:      2022-09-03 21:51:54
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { postRequest } from '/src/lib/axios';

export const dataTracerApi = {
  // 分页查询业务操作日志 - @author 卓大
  queryList: (param) => {
    return postRequest('/support/dataTracer/query', param);
  },

};
