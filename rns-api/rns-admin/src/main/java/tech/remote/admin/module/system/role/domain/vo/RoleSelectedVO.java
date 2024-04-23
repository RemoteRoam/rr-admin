package tech.remote.admin.module.system.role.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 选择角色
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-04-08 21:53:04
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RoleSelectedVO extends RoleVO {

    @Schema(description = "角色名称")
    private Boolean selected;
}
