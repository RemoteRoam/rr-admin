package tech.remote.admin.module.business.systemcertification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationAddForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationQueryForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationUpdateForm;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationVO;
import tech.remote.admin.module.business.systemcertification.service.SystemCertificationService;
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

/**
 * 体系认证表 Controller
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = AdminSwaggerTagConst.ProjectManagement.SYSTEM_CERTIFICATION)
public class SystemCertificationController {

    @Resource
    private SystemCertificationService systemCertificationService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/systemCertification/queryPage")
    public ResponseDTO<PageResult<SystemCertificationVO>> queryPage(@RequestBody @Valid SystemCertificationQueryForm queryForm) {
        return ResponseDTO.ok(systemCertificationService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/systemCertification/add")
    public ResponseDTO<String> add(@RequestBody @Valid SystemCertificationAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return systemCertificationService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/systemCertification/update")
    public ResponseDTO<String> update(@RequestBody @Valid SystemCertificationUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return systemCertificationService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/systemCertification/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return systemCertificationService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/systemCertification/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return systemCertificationService.delete(id);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/systemCertification/get/{id}")
    public ResponseDTO<SystemCertificationVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(systemCertificationService.getDetail(id));
    }
}
