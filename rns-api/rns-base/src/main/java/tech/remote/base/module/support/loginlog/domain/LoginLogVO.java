package tech.remote.base.module.support.loginlog.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.enumeration.UserTypeEnum;
import tech.remote.base.common.swagger.SchemaEnum;
import tech.remote.base.module.support.loginlog.LoginLogResultEnum;

import java.time.LocalDateTime;

/**
 * 登录日志
 *
 * @Author 远游工作室-yy
 * @Date 2022/07/22 19:46:23
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class LoginLogVO {

    private Long loginLogId;

    @Schema(description = "用户id")
    private Long userId;

    @SchemaEnum(value = UserTypeEnum.class, desc = "用户类型")
    private Integer userType;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "登录ip")
    private String loginIp;

    @Schema(description = "登录ip地区")
    private String loginIpRegion;

    @Schema(description = "user-agent")
    private String userAgent;

    @Schema(description = "remark")
    private String remark;

    @SchemaEnum(LoginLogResultEnum.class)
    private Integer loginResult;

    private LocalDateTime createTime;

}
