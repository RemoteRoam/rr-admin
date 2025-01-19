package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeItemVO;
import tech.remote.admin.module.business.goods.service.StocktakeItemService;
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
 * 盘点明细表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class StocktakeItemController {

    @Resource
    private StocktakeItemService stocktakeItemService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/stocktakeItem/queryPage")
    public ResponseDTO<PageResult<StocktakeItemVO>> queryPage(@RequestBody @Valid StocktakeItemQueryForm queryForm) {
        return ResponseDTO.ok(stocktakeItemService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/stocktakeItem/add")
    public ResponseDTO<String> add(@RequestBody @Valid StocktakeItemAddForm addForm) {
        return stocktakeItemService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/stocktakeItem/update")
    public ResponseDTO<String> update(@RequestBody @Valid StocktakeItemUpdateForm updateForm) {
        return stocktakeItemService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/stocktakeItem/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return stocktakeItemService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/stocktakeItem/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return stocktakeItemService.delete(id);
    }
}
