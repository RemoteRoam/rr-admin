package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.InventoryAddForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryQueryForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryVO;
import tech.remote.admin.module.business.goods.service.InventoryService;
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
 * 库存表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/inventory/queryPage")
    public ResponseDTO<PageResult<InventoryVO>> queryPage(@RequestBody @Valid InventoryQueryForm queryForm) {
        return ResponseDTO.ok(inventoryService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/inventory/add")
    public ResponseDTO<String> add(@RequestBody @Valid InventoryAddForm addForm) {
        return inventoryService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/inventory/update")
    public ResponseDTO<String> update(@RequestBody @Valid InventoryUpdateForm updateForm) {
        return inventoryService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/inventory/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Integer> idList) {
        return inventoryService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/inventory/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Integer id) {
        return inventoryService.delete(id);
    }
}
