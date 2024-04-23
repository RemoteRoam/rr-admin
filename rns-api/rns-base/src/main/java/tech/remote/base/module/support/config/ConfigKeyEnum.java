package tech.remote.base.module.support.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 系统配置常量类
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-14 20:46:27
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Getter
@AllArgsConstructor
public enum ConfigKeyEnum implements BaseEnum {

    /**
     * 万能密码
     */
    SUPER_PASSWORD("super_password", "万能密码"),

    ;

    private final String value;

    private final String desc;
}
