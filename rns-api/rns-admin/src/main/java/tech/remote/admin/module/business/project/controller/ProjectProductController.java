package tech.remote.admin.module.business.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.project.domain.form.ProjectProductAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.service.ProjectProductService;
import tech.remote.base.common.domain.ValidateList;
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
 * 项目产品表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectProductController {

    @Resource
    private ProjectProductService projectProductService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectProduct/queryPage")
    public ResponseDTO<PageResult<ProjectProductVO>> queryPage(@RequestBody @Valid ProjectProductQueryForm queryForm) {
        return ResponseDTO.ok(projectProductService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectProduct/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectProductAddForm addForm) {
        return projectProductService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectProduct/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectProductUpdateForm updateForm) {
        return projectProductService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/projectProduct/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return projectProductService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/projectProduct/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return projectProductService.delete(id);
    }
}
