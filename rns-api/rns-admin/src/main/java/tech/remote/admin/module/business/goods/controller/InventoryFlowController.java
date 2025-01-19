package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowAddForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowQueryForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryFlowVO;
import tech.remote.admin.module.business.goods.service.InventoryFlowService;
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
 * 库存流水表 Controller
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class InventoryFlowController {

    @Resource
    private InventoryFlowService inventoryFlowService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/inventoryFlow/queryPage")
    public ResponseDTO<PageResult<InventoryFlowVO>> queryPage(@RequestBody @Valid InventoryFlowQueryForm queryForm) {
        return ResponseDTO.ok(inventoryFlowService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/inventoryFlow/add")
    public ResponseDTO<String> add(@RequestBody @Valid InventoryFlowAddForm addForm) {
        return inventoryFlowService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/inventoryFlow/update")
    public ResponseDTO<String> update(@RequestBody @Valid InventoryFlowUpdateForm updateForm) {
        return inventoryFlowService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/inventoryFlow/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return inventoryFlowService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/inventoryFlow/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return inventoryFlowService.delete(id);
    }
}
