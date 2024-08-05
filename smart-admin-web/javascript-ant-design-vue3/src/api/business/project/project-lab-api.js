/**
 * 项目实验室任务表 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-05-15 13:19:26
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest, postDownload } from '/@/lib/axios';

export const projectLabApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage: (param) => {
    return postRequest('/projectLab/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
    return postRequest('/projectLab/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
    return postRequest('/projectLab/update', param);
  },

  // 查询详情 @author cbh
  detail: (id) => {
    return getRequest(`/projectLab/get/${id}`);
  },

  // 获取实验室项目进度 @author cbh
  getProgress: (progressCode) => {
    return getRequest(`/project/getProgress/${progressCode}`);
  },

  /**
    * 自定义分页查询  @author  cbh
    */
  queryLabListPage: (param) => {
    return postRequest('/projectLab/queryLabListPage', param);
  },

  /**
    * 实验室待办查询  @author  cbh
    */
  queryLabTodoList: (param) => {
    return postRequest('/projectLab/queryLabTodoList', param);
  },

  /**
    * 实验室预警分页查询  @author  cbh
    */
  queryLabAlarmListPage: (param) => {
    return postRequest('/projectLab/queryLabAlarmListPage', param);
  },

  // 导出excel @author cbh
  exportExcel: (param) => {
    return postDownload('/projectLab/exportExcel', param);
  },
};
