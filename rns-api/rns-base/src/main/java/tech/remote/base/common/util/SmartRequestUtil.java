package tech.remote.base.common.util;

import lombok.extern.slf4j.Slf4j;
import tech.remote.base.common.domain.RequestUser;

/**
 * 请求用户  工具类
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-05-30 21:22:12
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Slf4j
public class SmartRequestUtil {

    private static final ThreadLocal<RequestUser> REQUEST_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRequestUser(RequestUser requestUser) {
        REQUEST_THREAD_LOCAL.set(requestUser);
    }

    public static RequestUser getRequestUser() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public static Long getRequestUserId() {
        RequestUser requestUser = getRequestUser();
        return null == requestUser ? null : requestUser.getUserId();
    }


    public static void remove() {
        REQUEST_THREAD_LOCAL.remove();
    }


}
