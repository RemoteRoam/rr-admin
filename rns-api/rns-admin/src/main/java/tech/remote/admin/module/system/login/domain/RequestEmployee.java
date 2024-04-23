package tech.remote.admin.module.system.login.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.enumeration.GenderEnum;
import tech.remote.base.common.enumeration.UserTypeEnum;
import tech.remote.base.common.swagger.SchemaEnum;

/**
 * 请求员工登录信息
 *
 * @Author 远游工作室: 善逸
 * @Date 2021/8/4 21:15
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RequestEmployee implements RequestUser {

    @Schema(description = "员工id")
    private Long employeeId;

    @SchemaEnum(UserTypeEnum.class)
    private UserTypeEnum userType;

    @Schema(description = "登录账号")
    private String loginName;

    @Schema(description = "员工名称")
    private String actualName;

    @SchemaEnum(GenderEnum.class)
    private Integer gender;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "部门id")
    private Long departmentId;

    @Schema(description = "部门名称")
    private String departmentName;

    @Schema(description = "是否为超管")
    private Boolean administratorFlag;

    @Schema(description = "请求ip")
    private String ip;

    @Schema(description = "请求user-agent")
    private String userAgent;

    @Override
    public Long getUserId() {
        return employeeId;
    }

    @Override
    public String getUserName() {
        return actualName;
    }
}
