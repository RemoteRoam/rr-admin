/*
 * 首页路由
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 20:51:41
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { HOME_PAGE_NAME } from '/@/constants/system/home-const';
import { MENU_TYPE_ENUM } from '/@/constants/system/menu-const';
import SmartLayout from '/@/layout/index.vue';

export const homeRouters = [
  {
    path: '/',
    name: '_home',
    redirect: { name: HOME_PAGE_NAME },
    component: SmartLayout,
    meta: {
      title: '首页',
      menuType: MENU_TYPE_ENUM.CATALOG.value,
      icon: 'HomeOutlined',
    },
    children: [
      {
        path: '/home',
        name: HOME_PAGE_NAME,
        meta: {
          title: '首页',
          menuType: MENU_TYPE_ENUM.MENU.value,
          icon: 'HomeOutlined',
          parentMenuList: [{ name: '_home', title: '首页' }],
        },
        component: () => import('/@/views/system/home/index.vue'),
      },
    ],
  },
];
