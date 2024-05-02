package tech.remote.base.common.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统环境枚举类
 *
 * @Author 远游工作室-yy
 * @Date 2020-10-15 22:45:04
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@AllArgsConstructor
@Getter
public enum ProjectTypeEnum implements BaseEnum {

    SC_INITIALIZATION(41, "体系初始"),

    SC_SUPERVISION(42, "体系监督"),

    SC_RE_CERTIFICATION(43, "再认证"),

    SC_EXPANSION(44, "扩项");

    private final Integer value;

    private final String desc;
}
