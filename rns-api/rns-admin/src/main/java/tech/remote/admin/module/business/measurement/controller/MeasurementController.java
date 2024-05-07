package tech.remote.admin.module.business.measurement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.admin.module.business.measurement.service.MeasurementService;
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
 * 仪器计量表 Controller
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class MeasurementController {

    @Resource
    private MeasurementService measurementService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/measurement/queryPage")
    public ResponseDTO<PageResult<MeasurementVO>> queryPage(@RequestBody @Valid MeasurementQueryForm queryForm) {
        return ResponseDTO.ok(measurementService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/measurement/add")
    public ResponseDTO<String> add(@RequestBody @Valid MeasurementAddForm addForm) {
        return measurementService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/measurement/update")
    public ResponseDTO<String> update(@RequestBody @Valid MeasurementUpdateForm updateForm) {
        return measurementService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/measurement/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return measurementService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/measurement/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return measurementService.delete(id);
    }
}
