package tech.remote.admin.module.system.login.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.system.menu.domain.vo.MenuVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录结果信息
 *
 * @Author 远游工作室: 开云
 * @Date 2021-12-19 11:49:45
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class LoginResultVO extends RequestEmployee {

    @Schema(description = "token")
    private String token;

    @Schema(description = "菜单列表")
    private List<MenuVO> menuList;

    @Schema(description = "上次登录ip")
    private String lastLoginIp;

    @Schema(description = "上次登录ip地区")
    private String lastLoginIpRegion;

    @Schema(description = "上次登录user-agent")
    private String lastLoginUserAgent;

    @Schema(description = "上次登录时间")
    private LocalDateTime lastLoginTime;

}
