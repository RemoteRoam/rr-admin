/**
 * 项目认证费表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-05-15 13:46:16
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const projectCertificationFeeApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/projectCertificationFee/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/projectCertificationFee/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/projectCertificationFee/update', param);
  },



};
