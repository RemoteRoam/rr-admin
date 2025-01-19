package tech.remote.admin.module.system.support;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.helpdoc.domain.form.*;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocDetailVO;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocVO;
import tech.remote.base.module.support.helpdoc.service.HelpDocCatalogService;
import tech.remote.base.module.support.helpdoc.service.HelpDocService;
import tech.remote.base.module.support.repeatsubmit.annoation.RepeatSubmit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
public class AdminHelpDocController extends SupportBaseController {

    @Resource
    private HelpDocService helpDocService;

    @Resource
    private HelpDocCatalogService helpDocCatalogService;

    // --------------------- 帮助文档 【目录管理】 -------------------------


    @Operation(summary = "帮助文档目录-添加 @author 卓大")
    @PostMapping("/helpDoc/helpDocCatalog/add")
    public ResponseDTO<String> addHelpDocCatalog(@RequestBody @Valid HelpDocCatalogAddForm helpDocCatalogAddForm) {
        return helpDocCatalogService.add(helpDocCatalogAddForm);
    }

    @Operation(summary = "帮助文档目录-更新 @author 卓大")
    @PostMapping("/helpDoc/helpDocCatalog/update")
    public ResponseDTO<String> updateHelpDocCatalog(@RequestBody @Valid HelpDocCatalogUpdateForm helpDocCatalogUpdateForm) {
        return helpDocCatalogService.update(helpDocCatalogUpdateForm);
    }

    @Operation(summary = "帮助文档目录-删除 @author 卓大")
    @GetMapping("/helpDoc/helpDocCatalog/delete/{helpDocCatalogId}")
    public ResponseDTO<String> deleteHelpDocCatalog(@PathVariable Long helpDocCatalogId) {
        return helpDocCatalogService.delete(helpDocCatalogId);
    }

    // --------------------- 帮助文档 【管理:增、删、查、改】-------------------------

    @Operation(summary = "【管理】帮助文档-分页查询 @author 卓大")
    @PostMapping("/helpDoc/query")
    @SaCheckPermission("support:helpDoc:query")
    public ResponseDTO<PageResult<HelpDocVO>> query(@RequestBody @Valid HelpDocQueryForm queryForm) {
        return ResponseDTO.ok(helpDocService.query(queryForm));
    }

    @Operation(summary = "【管理】帮助文档-获取详情 @author 卓大")
    @GetMapping("/helpDoc/getDetail/{helpDocId}")
    @SaCheckPermission("support:helpDoc:add")
    public ResponseDTO<HelpDocDetailVO> getDetail(@PathVariable Long helpDocId) {
        return ResponseDTO.ok(helpDocService.getDetail(helpDocId));
    }

    @Operation(summary = "【管理】帮助文档-添加 @author 卓大")
    @PostMapping("/helpDoc/add")
    @RepeatSubmit
    public ResponseDTO<String> add(@RequestBody @Valid HelpDocAddForm addForm) {
        return helpDocService.add(addForm);
    }

    @Operation(summary = "【管理】帮助文档-更新 @author 卓大")
    @PostMapping("/helpDoc/update")
    @RepeatSubmit
    public ResponseDTO<String> update(@RequestBody @Valid HelpDocUpdateForm updateForm) {
        return helpDocService.update(updateForm);
    }

    @Operation(summary = "【管理】帮助文档-删除 @author 卓大")
    @GetMapping("/helpDoc/delete/{helpDocId}")
    public ResponseDTO<String> delete(@PathVariable Long helpDocId) {
        return helpDocService.delete(helpDocId);
    }

    @Operation(summary = "【管理】帮助文档-根据关联id查询 @author 卓大")
    @GetMapping("/helpDoc/queryHelpDocByRelationId/{relationId}")
    public ResponseDTO<List<HelpDocVO>> queryHelpDocByRelationId(@PathVariable Long relationId) {
        return ResponseDTO.ok(helpDocService.queryHelpDocByRelationId(relationId));
    }

}