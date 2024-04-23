package tech.remote.base.module.support.table;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.repeatsubmit.annoation.RepeatSubmit;
import tech.remote.base.module.support.table.domain.TableColumnUpdateForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 表格自定义列（前端用户自定义表格列，并保存到数据库里）
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-12 22:52:21
 *
 *
 * @Copyright <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@RestController
@Tag(name = SwaggerTagConst.Support.TABLE_COLUMN)
public class TableColumnController extends SupportBaseController {

    @Resource
    private TableColumnService tableColumnService;

    @Operation(summary = "修改表格列 @author 卓大")
    @PostMapping("/tableColumn/update")
    @RepeatSubmit
    public ResponseDTO<String> updateTableColumn(@RequestBody @Valid TableColumnUpdateForm updateForm) {
        return tableColumnService.updateTableColumns(SmartRequestUtil.getRequestUser(), updateForm);
    }

    @Operation(summary = "恢复默认（删除） @author 卓大")
    @GetMapping("/tableColumn/delete/{tableId}")
    @RepeatSubmit
    public ResponseDTO<String> deleteTableColumn(@PathVariable Integer tableId) {
        return tableColumnService.deleteTableColumn(SmartRequestUtil.getRequestUser(), tableId);
    }

    @Operation(summary = "查询表格列 @author 卓大")
    @GetMapping("/tableColumn/getColumns/{tableId}")
    public ResponseDTO<String> getColumns(@PathVariable Integer tableId) {
        return ResponseDTO.ok(tableColumnService.getTableColumns(SmartRequestUtil.getRequestUser(), tableId));
    }
}
