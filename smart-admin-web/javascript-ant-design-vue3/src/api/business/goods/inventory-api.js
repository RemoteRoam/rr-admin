/**
 * 库存表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-06-06 13:42:51
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const inventoryApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/inventory/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/inventory/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/inventory/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/inventory/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/inventory/batchDelete', idList);
  },

};
