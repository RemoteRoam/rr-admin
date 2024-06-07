package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseItemVO;
import tech.remote.admin.module.business.goods.service.PurchaseItemService;
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
 * 采购入库明细表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class PurchaseItemController {

    @Resource
    private PurchaseItemService purchaseItemService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/purchaseItem/queryPage")
    public ResponseDTO<PageResult<PurchaseItemVO>> queryPage(@RequestBody @Valid PurchaseItemQueryForm queryForm) {
        return ResponseDTO.ok(purchaseItemService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/purchaseItem/add")
    public ResponseDTO<String> add(@RequestBody @Valid PurchaseItemAddForm addForm) {
        return purchaseItemService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/purchaseItem/update")
    public ResponseDTO<String> update(@RequestBody @Valid PurchaseItemUpdateForm updateForm) {
        return purchaseItemService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/purchaseItem/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return purchaseItemService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/purchaseItem/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return purchaseItemService.delete(id);
    }
}
