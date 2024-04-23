package tech.remote.admin.module.system.datascope.constant;


import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 数据范围 sql where
 *
 * @Author 远游工作室: 罗伊
 * @Date 2020/11/28  20:59:17
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum DataScopeWhereInTypeEnum implements BaseEnum {

    /**
     * 以员工IN
     */
    EMPLOYEE(0, "以员工IN"),

    /**
     * 以部门IN
     */
    DEPARTMENT(1, "以部门IN"),

    /**
     * 自定义策略
     */
    CUSTOM_STRATEGY(2, "自定义策略");

    private final Integer value;
    private final String desc;

    DataScopeWhereInTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}
