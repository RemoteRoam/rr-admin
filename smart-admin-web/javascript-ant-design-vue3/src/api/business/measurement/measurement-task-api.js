/**
 * 仪器计量实验室任务表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-05-07 14:44:51
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const measurementTaskApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/measurementTask/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/measurementTask/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/measurementTask/update', param);
  },



};
