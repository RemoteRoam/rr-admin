package tech.remote.admin.module.business.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.form.SkusAddForm;
import tech.remote.admin.module.business.goods.domain.form.SkusQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SkusUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.SkusVO;
import tech.remote.admin.module.business.goods.service.SkusService;
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
 * 型号规格 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class SkusController {

    @Resource
    private SkusService skusService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/skus/queryPage")
    public ResponseDTO<PageResult<SkusVO>> queryPage(@RequestBody @Valid SkusQueryForm queryForm) {
        return ResponseDTO.ok(skusService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/skus/add")
    public ResponseDTO<String> add(@RequestBody @Valid SkusAddForm addForm) {
        return skusService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/skus/update")
    public ResponseDTO<String> update(@RequestBody @Valid SkusUpdateForm updateForm) {
        return skusService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/skus/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Integer> idList) {
        return skusService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/skus/delete/{skuId}")
    public ResponseDTO<String> batchDelete(@PathVariable Integer skuId) {
        return skusService.delete(skuId);
    }
}
