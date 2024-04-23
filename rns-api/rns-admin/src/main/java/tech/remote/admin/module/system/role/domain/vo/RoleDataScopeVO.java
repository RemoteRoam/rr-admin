package tech.remote.admin.module.system.role.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色的数据范围
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RoleDataScopeVO {

    @Schema(description = "数据范围id")
    private Integer dataScopeType;

    @Schema(description = "可见范围")
    private Integer viewType;
}
