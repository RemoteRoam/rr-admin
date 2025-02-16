package tech.remote.admin.module.system.menu.constant;


import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 权限类型
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-03-06 22:04:37
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum MenuPermsTypeEnum implements BaseEnum {
    /**
     * sa-token
     */
    SA_TOKEN(1, "Sa-Token模式"),

    ;

    private final Integer value;

    private final String desc;


    MenuPermsTypeEnum(Integer value, String desc) {
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
