package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.SalesAddForm;
import tech.remote.admin.module.business.goods.domain.form.SalesQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SalesUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesVO;
import tech.remote.admin.module.business.goods.service.SalesService;
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
 * 销售单表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class SalesController {

    @Resource
    private SalesService salesService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/sales/queryPage")
    public ResponseDTO<PageResult<SalesVO>> queryPage(@RequestBody @Valid SalesQueryForm queryForm) {
        return ResponseDTO.ok(salesService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/sales/add")
    public ResponseDTO<String> add(@RequestBody @Valid SalesAddForm addForm) {
        return salesService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/sales/update")
    public ResponseDTO<String> update(@RequestBody @Valid SalesUpdateForm updateForm) {
        return salesService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/sales/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return salesService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/sales/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return salesService.delete(id);
    }
}
