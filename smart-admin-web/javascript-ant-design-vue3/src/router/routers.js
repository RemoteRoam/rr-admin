/*
 * 所有路由入口
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 20:52:26
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { homeRouters } from './system/home';
import { loginRouters } from './system/login';
import { helpDocRouters } from './support/help-doc';
import NotFound from '/@/views/system/40X/404.vue';
import NoPrivilege from '/@/views/system/40X/403.vue';
import ProjectProgress from '/@/views/business/external/project-progress.vue';

export const routerArray = [
    ...loginRouters,
    ...homeRouters,
    ...helpDocRouters,
    { path: '/project/progress', name: '项目进度', component: ProjectProgress },
    { path: '/:pathMatch(.*)*', name: '404', component: NotFound },
    { path: '/403', name: '403', component: NoPrivilege }
];
