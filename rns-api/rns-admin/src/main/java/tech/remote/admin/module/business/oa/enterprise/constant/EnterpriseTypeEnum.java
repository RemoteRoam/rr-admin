package tech.remote.admin.module.business.oa.enterprise.constant;


import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 企业类型
 *
 * @Author 远游工作室: 开云
 * @Date 2022/7/28 20:37:15
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum EnterpriseTypeEnum implements BaseEnum {

    /**
     * 有限企业
     */
    NORMAL(1, "有限企业"),

    /**
     * 外资企业
     */
    FOREIGN(2, "外资企业"),
    ;

    private Integer value;
    private String desc;

    EnterpriseTypeEnum(Integer value, String desc) {
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
