package tech.remote.admin.util;

import tech.remote.admin.module.system.login.domain.RequestEmployee;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.util.SmartRequestUtil;

/**
 * admin 端的请求工具类
 *
 * @Author 远游工作室-yy
 * @Date 2023/7/28 19:39:21
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>，Since 2012
 */
public final class AdminRequestUtil {


    public static RequestEmployee getRequestUser() {
        return (RequestEmployee) SmartRequestUtil.getRequestUser();
    }

    public static Long getRequestUserId() {
        RequestUser requestUser = getRequestUser();
        return null == requestUser ? null : requestUser.getUserId();
    }


}
