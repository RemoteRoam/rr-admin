package tech.remote.admin.module.business.project.controller;

import tech.remote.admin.module.business.project.domain.form.ProjectLabAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabVO;
import tech.remote.admin.module.business.project.service.ProjectLabService;
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
 * 项目实验室任务表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectLabController {

    @Resource
    private ProjectLabService projectLabService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectLab/queryPage")
    public ResponseDTO<PageResult<ProjectLabVO>> queryPage(@RequestBody @Valid ProjectLabQueryForm queryForm) {
        return ResponseDTO.ok(projectLabService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectLab/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectLabAddForm addForm) {
        return projectLabService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectLab/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectLabUpdateForm updateForm) {
        return projectLabService.update(updateForm);
    }

}
