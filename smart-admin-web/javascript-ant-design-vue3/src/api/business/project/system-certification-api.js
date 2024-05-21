/**
 * 体系认证表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-04-25 14:53:11
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest, postDownload } from '/@/lib/axios';

export const systemCertificationApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/systemCertification/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/systemCertification/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/systemCertification/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/systemCertification/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/systemCertification/batchDelete', idList);
  },

  // 查询详情 @author cbh
  detail: (id) => {
      return getRequest(`/systemCertification/get/${id}`);
  },

    // 导出excel @author cbh
    exportExcel: (param) => {
        return postDownload('/systemCertification/exportExcel', param);
    },

};
