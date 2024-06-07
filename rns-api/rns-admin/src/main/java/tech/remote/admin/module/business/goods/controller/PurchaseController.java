package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.PurchaseAddForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseQueryForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseVO;
import tech.remote.admin.module.business.goods.service.PurchaseService;
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
 * 采购入库表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/purchase/queryPage")
    public ResponseDTO<PageResult<PurchaseVO>> queryPage(@RequestBody @Valid PurchaseQueryForm queryForm) {
        return ResponseDTO.ok(purchaseService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/purchase/add")
    public ResponseDTO<String> add(@RequestBody @Valid PurchaseAddForm addForm) {
        return purchaseService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/purchase/update")
    public ResponseDTO<String> update(@RequestBody @Valid PurchaseUpdateForm updateForm) {
        return purchaseService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/purchase/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return purchaseService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/purchase/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return purchaseService.delete(id);
    }
}
