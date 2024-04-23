package tech.remote.admin.module.system.role.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;

/**
 * 角色的员工查询
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RoleEmployeeQueryForm extends PageParam {

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "角色id")
    private String roleId;
}
