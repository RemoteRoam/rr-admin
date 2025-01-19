package tech.remote.admin.module.business.systemcertificationnode.controller;

import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeAddForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeQueryForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeUpdateForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.admin.module.business.systemcertificationnode.service.SystemCertificationNodeService;
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
 * 体系认证项目流程节点表 Controller
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class SystemCertificationNodeController {

    @Resource
    private SystemCertificationNodeService systemCertificationNodeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/systemCertificationNode/queryPage")
    public ResponseDTO<PageResult<SystemCertificationNodeVO>> queryPage(@RequestBody @Valid SystemCertificationNodeQueryForm queryForm) {
        return ResponseDTO.ok(systemCertificationNodeService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/systemCertificationNode/add")
    public ResponseDTO<String> add(@RequestBody @Valid SystemCertificationNodeAddForm addForm) {
        return systemCertificationNodeService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/systemCertificationNode/update")
    public ResponseDTO<String> update(@RequestBody @Valid SystemCertificationNodeUpdateForm updateForm) {
        return systemCertificationNodeService.update(updateForm);
    }

}
