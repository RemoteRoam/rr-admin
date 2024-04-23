package tech.remote.base.constant;

import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 登录设备类型
 *
 * @Author 远游工作室-yy
 * @Date 2021-11-29 19:48:35
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum LoginDeviceEnum implements BaseEnum {

    PC(1, "电脑端"),

    ANDROID(2, "安卓"),

    APPLE(3, "苹果"),

    H5(4, "H5"),

    WEIXIN_MP(5, "微信小程序");

    LoginDeviceEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
