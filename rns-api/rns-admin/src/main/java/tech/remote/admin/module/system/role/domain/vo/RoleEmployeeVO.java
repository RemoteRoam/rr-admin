package tech.remote.admin.module.system.role.domain.vo;

import lombok.Data;

/**
 * 角色的员工
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RoleEmployeeVO {

    private Long roleId;

    private Long employeeId;

    private String roleName;
}
