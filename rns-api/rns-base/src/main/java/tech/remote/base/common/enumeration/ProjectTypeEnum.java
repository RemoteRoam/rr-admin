package tech.remote.base.common.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目类型枚举类
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

    PC_CCC(11, "CCC自我声明"),
    PC_CERTIFICATION(12, "产品认证"),
    LAB_PROJECT(21, "试验项目"),
    PC_SUPERVISION(31, "产品监督"),
    SC_INITIALIZATION(41, "体系初始"),
    SC_SUPERVISION(42, "体系监督"),
    SC_RE_CERTIFICATION(43, "再认证"),
    SC_EXPANSION(44, "扩项"),
    MEASUREMENT(51, "仪器计量");

    private final Integer value;

    private final String desc;
}
