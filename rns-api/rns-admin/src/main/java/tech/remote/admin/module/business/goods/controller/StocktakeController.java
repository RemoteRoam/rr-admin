package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.StocktakeAddForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeQueryForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeVO;
import tech.remote.admin.module.business.goods.service.StocktakeService;
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
 * 盘点表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class StocktakeController {

    @Resource
    private StocktakeService stocktakeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/stocktake/queryPage")
    public ResponseDTO<PageResult<StocktakeVO>> queryPage(@RequestBody @Valid StocktakeQueryForm queryForm) {
        return ResponseDTO.ok(stocktakeService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/stocktake/add")
    public ResponseDTO<String> add(@RequestBody @Valid StocktakeAddForm addForm) {
        return stocktakeService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/stocktake/update")
    public ResponseDTO<String> update(@RequestBody @Valid StocktakeUpdateForm updateForm) {
        return stocktakeService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/stocktake/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return stocktakeService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/stocktake/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return stocktakeService.delete(id);
    }
}
