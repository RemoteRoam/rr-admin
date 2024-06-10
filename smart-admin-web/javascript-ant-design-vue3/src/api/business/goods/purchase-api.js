/**
 * 采购入库表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-06-06 13:16:47
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const purchaseApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage: (param) => {
    return postRequest('/purchase/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
    return postRequest('/purchase/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
    return postRequest('/purchase/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
    return getRequest(`/purchase/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
    return postRequest('/purchase/batchDelete', idList);
  },

  /**
   * 通过id查询  @param id  主键id
   */
  getById: (id) => {
    return getRequest(`/purchase/get/${id}`);
  }

};
