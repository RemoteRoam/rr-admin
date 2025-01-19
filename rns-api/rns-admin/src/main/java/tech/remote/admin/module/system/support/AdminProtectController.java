package tech.remote.admin.module.system.support;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.securityprotect.domain.LoginFailQueryForm;
import tech.remote.base.module.support.securityprotect.domain.LoginFailVO;
import tech.remote.base.module.support.securityprotect.service.ProtectLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * 网络安全
 *
 * @Author 远游工作室-yy
 * @Date 2023/10/17 19:07:27
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>，Since 2012
 */

@RestController
@Tag(name = SwaggerTagConst.Support.PROTECT)
public class AdminProtectController extends SupportBaseController {

    @Resource
    private ProtectLoginService protectLoginService;

    @Operation(summary = "分页查询 @author 远游工作室-主任-卓大")
    @PostMapping("/protect/loginFail/queryPage")
    public ResponseDTO<PageResult<LoginFailVO>> queryPage(@RequestBody @Valid LoginFailQueryForm queryForm) {
        return ResponseDTO.ok(protectLoginService.queryPage(queryForm));
    }


    @Operation(summary = "批量删除 @author 远游工作室-主任-卓大")
    @PostMapping("/protect/loginFail/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return protectLoginService.batchDelete(idList);
    }


}
