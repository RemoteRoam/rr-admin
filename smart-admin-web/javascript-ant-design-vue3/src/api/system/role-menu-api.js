/*
 * 角色菜单
 *
 * @Author:    YY Studio
 * @Date:      2022-09-03 22:00:49
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { getRequest, postRequest } from '/src/lib/axios';
export const roleMenuApi = {
  /**
   * @description: 获取角色关联菜单权限
   */
  getRoleSelectedMenu: (roleId) => {
    return getRequest(`role/menu/getRoleSelectedMenu/${roleId}`);
  },
  /**
   * @description: 更新角色权限
   */
  updateRoleMenu: (data) => {
    return postRequest('role/menu/updateRoleMenu', data);
  },
};
