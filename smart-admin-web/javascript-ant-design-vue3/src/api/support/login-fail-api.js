/**
 * 登录锁定 api 封装
 *
 * @Author:    YY Studio
 * @Date:      2023-10-17 18:02:37
 * @Copyright  YY Studio
 */
import { postRequest, getRequest } from '/src/lib/axios';

export const loginFailApi = {

  /**
   * 分页查询  @author  YY Studio
   */
  queryPage: (param) => {
    return postRequest('/support/protect/loginFail/queryPage', param);
  },

  /**
   * 批量删除  @author  YY Studio
   */
  batchDelete: (idList) => {
    return postRequest('/support/protect/loginFail/batchDelete', idList);
  },

};
