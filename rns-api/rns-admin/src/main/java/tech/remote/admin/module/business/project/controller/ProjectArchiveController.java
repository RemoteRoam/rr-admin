package tech.remote.admin.module.business.project.controller;

import tech.remote.admin.module.business.project.domain.form.ProjectArchiveAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectArchiveVO;
import tech.remote.admin.module.business.project.service.ProjectArchiveService;
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
 * 项目归档表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectArchiveController {

    @Resource
    private ProjectArchiveService projectArchiveService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectArchive/queryPage")
    public ResponseDTO<PageResult<ProjectArchiveVO>> queryPage(@RequestBody @Valid ProjectArchiveQueryForm queryForm) {
        return ResponseDTO.ok(projectArchiveService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectArchive/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectArchiveAddForm addForm) {
        return projectArchiveService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectArchive/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectArchiveUpdateForm updateForm) {
        return projectArchiveService.update(updateForm);
    }

}
