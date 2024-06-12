/**
 * 销售单表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-06-06 13:16:54
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const salesApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage: (param) => {
    return postRequest('/sales/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
    return postRequest('/sales/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
    return postRequest('/sales/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
    return getRequest(`/sales/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
    return postRequest('/sales/batchDelete', idList);
  },

  /**
   * 通过id查询  @param id  id
   */
  getById: (id) => {
    return getRequest(`/sales/detail/${id}`);
  }
};
