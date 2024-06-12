/**
 * 采购入库明细表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-06-06 13:16:54
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const purchaseItemApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/purchaseItem/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/purchaseItem/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/purchaseItem/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/purchaseItem/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/purchaseItem/batchDelete', idList);
  },

};
