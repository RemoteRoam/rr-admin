/**
 * 三方机构 api 封装
 *
 * @Author:    cbh
 * @Date:      2024-04-24 09:19:13
 * @Copyright  Remote Nomad Studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const thirdPartyApi = {

  /**
   * 分页查询  @author  cbh
   */
  queryPage : (param) => {
    return postRequest('/thirdParty/queryPage', param);
  },

  /**
   * 增加  @author  cbh
   */
  add: (param) => {
      return postRequest('/thirdParty/add', param);
  },

  /**
   * 修改  @author  cbh
   */
  update: (param) => {
      return postRequest('/thirdParty/update', param);
  },


  /**
   * 删除  @author  cbh
   */
  delete: (id) => {
      return getRequest(`/thirdParty/delete/${id}`);
  },

  /**
   * 批量删除  @author  cbh
   */
  batchDelete: (idList) => {
      return postRequest('/thirdParty/batchDelete', idList);
  },

  // 查询详情 @author cbh
  detail: (id) => {
      return getRequest(`/thirdParty/get/${id}`);
  },

    queryList: (type) => {
        let query = '';
        if (type) {
            query = `?type=${type}`;
        }
        return getRequest(`/thirdParty/query/list${query}`);
    },
};
