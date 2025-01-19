package tech.remote.admin.module.business.projectnode.controller;

import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeAddForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeUpdateForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.admin.module.business.projectnode.service.ProjectNodeService;
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
 * 项目流程节点表 Controller
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectNodeController {

    @Resource
    private ProjectNodeService projectNodeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectNode/queryPage")
    public ResponseDTO<PageResult<ProjectNodeVO>> queryPage(@RequestBody @Valid ProjectNodeQueryForm queryForm) {
        return ResponseDTO.ok(projectNodeService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectNode/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectNodeAddForm addForm) {
        return projectNodeService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectNode/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectNodeUpdateForm updateForm) {
        return projectNodeService.update(updateForm);
    }

}
