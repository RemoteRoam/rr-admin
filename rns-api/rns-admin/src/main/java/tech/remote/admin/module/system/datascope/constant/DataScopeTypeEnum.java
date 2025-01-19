package tech.remote.admin.module.system.datascope.constant;

import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 数据范围 类型
 *
 * @Author 远游工作室: 罗伊
 * @Date 2020/11/28  20:59:17
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum DataScopeTypeEnum implements BaseEnum {

    /**
     * 系统通知
     */
    NOTICE(1, 20, "系统通知", "系统通知数据范围"),
    ;

    private final Integer value;

    private final Integer sort;

    private final String name;

    private final String desc;

    DataScopeTypeEnum(Integer value, Integer sort, String name, String desc) {
        this.value = value;
        this.sort = sort;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public Integer getSort() {
        return sort;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }


}
