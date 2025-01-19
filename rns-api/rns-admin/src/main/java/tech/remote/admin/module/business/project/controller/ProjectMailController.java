package tech.remote.admin.module.business.project.controller;

import tech.remote.admin.module.business.project.domain.form.ProjectMailAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectMailQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectMailUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectMailVO;
import tech.remote.admin.module.business.project.service.ProjectMailService;
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
 * 项目邮寄表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectMailController {

    @Resource
    private ProjectMailService projectMailService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectMail/queryPage")
    public ResponseDTO<PageResult<ProjectMailVO>> queryPage(@RequestBody @Valid ProjectMailQueryForm queryForm) {
        return ResponseDTO.ok(projectMailService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectMail/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectMailAddForm addForm) {
        return projectMailService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectMail/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectMailUpdateForm updateForm) {
        return projectMailService.update(updateForm);
    }

}
