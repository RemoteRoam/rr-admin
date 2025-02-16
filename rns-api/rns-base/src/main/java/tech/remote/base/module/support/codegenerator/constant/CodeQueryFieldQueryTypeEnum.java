package tech.remote.base.module.support.codegenerator.constant;

import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 查询条件类型
 *
 * @Author 远游工作室-yy
 * @Date 2022-06-29 20:23:46
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum CodeQueryFieldQueryTypeEnum implements BaseEnum {

    LIKE("Like", "模糊查询"),
    EQUAL("Equal", "等于"),
    DATE_RANGE("DateRange", "日期范围"),
    DATE("Date", "指定日期"),
    ENUM("Enum", "枚举"),

    DICT("Dict", "字典"),
    ;

    private String value;

    private String desc;

    CodeQueryFieldQueryTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
