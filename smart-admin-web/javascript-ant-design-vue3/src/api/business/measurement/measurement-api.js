/**
 * 仪器计量表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-05-07 14:42:35
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const measurementApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/measurement/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/measurement/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/measurement/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/measurement/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/measurement/batchDelete', idList);
  },

  // 查询详情 @author cbh
  detail: (id) => {
      return getRequest(`/measurement/get/${id}`);
  },

};
