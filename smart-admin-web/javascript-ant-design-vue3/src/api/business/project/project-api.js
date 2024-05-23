/**
 * 项目表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-05-15 13:54:05
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest, postDownload } from '/@/lib/axios';

export const projectApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/project/queryPage', param);
  },

  /**
   * 待办列表分页查询  @author  cbh
   */
  queryToDoPage : (param) => {
    return postRequest('/project/queryToDoPage', param);
  },


  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/project/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/project/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/project/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/project/batchDelete', idList);
  },


  // 查询详情 @author cbh
  detail: (id) => {
      return getRequest(`/project/get/${id}`);
  },

  // 获取待处理的产品列表
  getPendingProduct:(id, nodeId) => {
      return getRequest(`/project/getPendingProduct/${id}/${nodeId}`);
  },

    // 导出excel @author cbh
    exportExcel: (param) => {
        return postDownload('/project/exportExcel', param);
    },

};
