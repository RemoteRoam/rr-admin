package tech.remote.base.module.support.codegenerator.constant;

import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 页面类型
 *
 * @Author 远游工作室-yy
 * @Date 2022-06-29 19:11:22
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum CodeGeneratorPageTypeEnum implements BaseEnum {

    MODAL("modal", "弹窗"),
    DRAWER("drawer", "抽屉"),
    PAGE("page", "新页面");

    private String value;

    private String desc;

    CodeGeneratorPageTypeEnum(String value, String desc) {
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
