package tech.remote.admin.module.system.department.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.system.employee.domain.vo.EmployeeVO;

import java.util.List;

/**
 * 部门
 *
 * @Author 远游工作室-yy
 * @Date 2022-01-12 20:37:48
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class DepartmentEmployeeTreeVO extends DepartmentVO {

    @Schema(description = "部门员工列表")
    private List<EmployeeVO> employees;

    @Schema(description = "子部门")
    private List<DepartmentEmployeeTreeVO> children;

}
