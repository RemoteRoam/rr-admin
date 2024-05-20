package tech.remote.admin.module.business.project.controller;

import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectCertificationFeeVO;
import tech.remote.admin.module.business.project.service.ProjectCertificationFeeService;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 项目认证费表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectCertificationFeeController {

    @Resource
    private ProjectCertificationFeeService projectCertificationFeeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectCertificationFee/queryPage")
    public ResponseDTO<PageResult<ProjectCertificationFeeVO>> queryPage(@RequestBody @Valid ProjectCertificationFeeQueryForm queryForm) {
        return ResponseDTO.ok(projectCertificationFeeService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectCertificationFee/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectCertificationFeeAddForm addForm) {
        return projectCertificationFeeService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectCertificationFee/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectCertificationFeeUpdateForm updateForm) {
        return projectCertificationFeeService.update(updateForm);
    }

}
