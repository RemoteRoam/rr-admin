package tech.remote.admin.module.system.support;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.loginlog.LoginLogService;
import tech.remote.base.module.support.loginlog.domain.LoginLogQueryForm;
import tech.remote.base.module.support.loginlog.domain.LoginLogVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录日志
 *
 * @Author 远游工作室-yy
 * @Date 2022/07/22 19:46:23
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@RestController
@Tag(name = SwaggerTagConst.Support.LOGIN_LOG)
public class AdminLoginLogController extends SupportBaseController {

    @Resource
    private LoginLogService loginLogService;

    @Operation(summary = "分页查询 @author 卓大")
    @PostMapping("/loginLog/page/query")
    @SaCheckPermission("support:loginLog:query")
    public ResponseDTO<PageResult<LoginLogVO>> queryByPage(@RequestBody LoginLogQueryForm queryForm) {
        return loginLogService.queryByPage(queryForm);
    }


}
