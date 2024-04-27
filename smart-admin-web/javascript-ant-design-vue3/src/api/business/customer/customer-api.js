/**
 * 客户表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-04-23 12:10:04
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const customerApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/customer/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/customer/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/customer/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/customer/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/customer/batchDelete', idList);
  },

  // 查询详情 @author cbh
  detail: (customerId) => {
      return getRequest(`/customer/get/${customerId}`);
  },

    queryList: () => {
        return getRequest(`/customer/query/list`);
    },

};
