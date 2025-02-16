/*
 *  员工
 *
 * @Author:    YY Studio
 * @Date:      2022-09-03 21:59:15
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */

import { getRequest, postRequest } from '/src/lib/axios';

export const employeeApi = {
  /**
   * 查询所有员工 @author 卓大
   */
  queryAll: () => {
    return getRequest('/employee/queryAll');
  },
  /**
   * 员工管理查询
   */
  queryEmployee: (params) => {
    return postRequest('/employee/query', params);
  },
  /**
   * 添加员工
   */
  addEmployee: (params) => {
    return postRequest('/employee/add', params);
  },
  /**
   * 更新员工信息
   */
  updateEmployee: (params) => {
    return postRequest('/employee/update', params);
  },
  /**
   * 删除员工
   */
  deleteEmployee: (employeeId) => {
    return getRequest(`/employee/delete/${employeeId}`);
  },
  /**
   * 批量删除员工
   */
  batchDeleteEmployee: (employeeIdList) => {
    return postRequest('/employee/update/batch/delete', employeeIdList);
  },
  /**
   * 批量调整员工部门
   */
  batchUpdateDepartmentEmployee: (updateParam) => {
    return postRequest('/employee/update/batch/department', updateParam);
  },
  /**
   * 重置员工密码
   */
  resetPassword: (employeeId) => {
    return getRequest(`/employee/update/password/reset/${employeeId}`);
  },
  /**
   * 修改面面
   */
  updateEmployeePassword: (param) => {
    return postRequest('/employee/update/password', param);
  },
  /**
   * 更新员工禁用状态
   */
  updateDisabled: (employeeId) => {
    return getRequest(`/employee/update/disabled/${employeeId}`);
  },

  /**
   * 查询员工-根据部门id
   */
  queryEmployeeByDeptId: (departmentId) => {
    return getRequest(`/employee/query/dept/${departmentId}`);
  },
};
