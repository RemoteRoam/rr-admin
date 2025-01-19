package tech.remote.base.common.enumeration;

/**
 * 用户类型
 *
 * @Author 远游工作室-yy
 * @Date 2022/10/19 21:46:24
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public enum UserTypeEnum implements BaseEnum {

    /**
     * 管理端 员工用户
     */
    ADMIN_EMPLOYEE(1, "员工");

    private Integer type;

    private String desc;

    UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
