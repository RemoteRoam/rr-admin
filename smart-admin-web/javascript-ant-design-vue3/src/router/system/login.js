/*
 * 登录页面
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 20:51:50
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */

export const loginRouters = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('/@/views/system/login3/login.vue'),
    meta: {
      title: '登录',
      hideInMenu: true,
    },
  },
];
