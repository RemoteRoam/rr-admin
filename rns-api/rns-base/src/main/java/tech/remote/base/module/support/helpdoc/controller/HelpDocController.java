package tech.remote.base.module.support.helpdoc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.helpdoc.domain.form.HelpDocViewRecordQueryForm;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocCatalogVO;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocDetailVO;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocVO;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocViewRecordVO;
import tech.remote.base.module.support.helpdoc.service.HelpDocCatalogService;
import tech.remote.base.module.support.helpdoc.service.HelpDocUserService;
import tech.remote.base.module.support.repeatsubmit.annoation.RepeatSubmit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 帮助文档
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-20 23:11:42
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Tag(name = SwaggerTagConst.Support.HELP_DOC)
@RestController
public class HelpDocController extends SupportBaseController {

    @Resource
    private HelpDocCatalogService helpDocCatalogService;

    @Resource
    private HelpDocUserService helpDocUserService;

    // --------------------- 帮助文档 【目录】 -------------------------

    @Operation(summary = "帮助文档目录-获取全部 @author 卓大")
    @GetMapping("/helpDoc/helpDocCatalog/getAll")
    public ResponseDTO<List<HelpDocCatalogVO>> getAll() {
        return ResponseDTO.ok(helpDocCatalogService.getAll());
    }

    // --------------------- 帮助文档 【用户】-------------------------

    @Operation(summary = "【用户】帮助文档-查看详情 @author 卓大")
    @GetMapping("/helpDoc/user/view/{helpDocId}")
    @RepeatSubmit
    public ResponseDTO<HelpDocDetailVO> view(@PathVariable Long helpDocId, HttpServletRequest request) {
        return helpDocUserService.view(
                SmartRequestUtil.getRequestUser(),
                helpDocId);
    }

    @Operation(summary = "【用户】帮助文档-查询全部 @author 卓大")
    @GetMapping("/helpDoc/user/queryAllHelpDocList")
    @RepeatSubmit
    public ResponseDTO<List<HelpDocVO>> queryAllHelpDocList() {
        return helpDocUserService.queryAllHelpDocList();
    }


    @Operation(summary = "【用户】帮助文档-查询 查看记录 @author 卓大")
    @PostMapping("/helpDoc/user/queryViewRecord")
    @RepeatSubmit
    public ResponseDTO<PageResult<HelpDocViewRecordVO>> queryViewRecord(@RequestBody @Valid HelpDocViewRecordQueryForm helpDocViewRecordQueryForm) {
        return ResponseDTO.ok(helpDocUserService.queryViewRecord(helpDocViewRecordQueryForm));
    }
}