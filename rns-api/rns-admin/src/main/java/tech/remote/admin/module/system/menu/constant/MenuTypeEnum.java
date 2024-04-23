package tech.remote.admin.module.system.menu.constant;


import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 菜单类型枚举
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-03-06 22:04:37
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum MenuTypeEnum implements BaseEnum {
    /**
     * 目录
     */
    CATALOG(1, "目录"),
    /**
     * 菜单
     */
    MENU(2, "菜单"),
    /**
     * 功能点
     */
    POINTS(3, "功能点");

    private final Integer value;

    private final String desc;


    MenuTypeEnum(Integer value, String desc) {
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
