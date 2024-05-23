package tech.remote.admin.module.business.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.project.domain.form.ProjectAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectToDoQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
import tech.remote.admin.module.business.project.service.ProjectService;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.util.SmartRequestUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 项目表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/project/queryPage")
    public ResponseDTO<PageResult<ProjectVO>> queryPage(@RequestBody @Valid ProjectQueryForm queryForm) {
        return ResponseDTO.ok(projectService.queryPage(queryForm));
    }

    @Operation(summary = "待办列表分页查询 @author cbh")
    @PostMapping("/project/queryToDoPage")
    public ResponseDTO<PageResult<ProjectVO>> queryToDoPage(@RequestBody @Valid ProjectToDoQueryForm queryForm) {
        return ResponseDTO.ok(projectService.queryToDoPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/project/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return projectService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/project/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return projectService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/project/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return projectService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/project/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return projectService.delete(id);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/project/get/{id}")
    public ResponseDTO<ProjectVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(projectService.getDetail(id));
    }

    @Operation(summary = "查询所有产品列表 @author cbh")
    @GetMapping("/project/getAllProduct/{id}")
    public ResponseDTO<List<ProjectProductVO>> getAllProduct(@PathVariable Long id) {
        return ResponseDTO.ok(projectService.getAllProduct(id));
    }

    @Operation(summary = "查询待处理的产品列表 @author cbh")
    @GetMapping("/project/getPendingProduct/{id}/{nodeId}")
    public ResponseDTO<List<ProjectProductVO>> getPendingProduct(@PathVariable Long id, @PathVariable Integer nodeId) {
        return ResponseDTO.ok(projectService.getPendingProduct(id, nodeId));
    }
}
