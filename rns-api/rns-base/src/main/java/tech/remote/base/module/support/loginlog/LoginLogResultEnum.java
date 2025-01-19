package tech.remote.base.module.support.loginlog;

import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 登录类型
 *
 * @Author 远游工作室-yy
 * @Date 2022/07/22 19:46:23
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum LoginLogResultEnum implements BaseEnum {
    
    LOGIN_SUCCESS(0, "登录成功"),
    LOGIN_FAIL(1, "登录失败"),
    LOGIN_OUT(2, "退出登录");

    private Integer type;
    private String desc;

    LoginLogResultEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
