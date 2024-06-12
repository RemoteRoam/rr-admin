package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.SalesItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.SalesItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SalesItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesItemVO;
import tech.remote.admin.module.business.goods.service.SalesItemService;
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
 * 销售明细表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class SalesItemController {

    @Resource
    private SalesItemService salesItemService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/salesItem/queryPage")
    public ResponseDTO<PageResult<SalesItemVO>> queryPage(@RequestBody @Valid SalesItemQueryForm queryForm) {
        return ResponseDTO.ok(salesItemService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/salesItem/add")
    public ResponseDTO<String> add(@RequestBody @Valid SalesItemAddForm addForm) {
        return salesItemService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/salesItem/update")
    public ResponseDTO<String> update(@RequestBody @Valid SalesItemUpdateForm updateForm) {
        return salesItemService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/salesItem/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return salesItemService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/salesItem/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return salesItemService.delete(id);
    }
}
