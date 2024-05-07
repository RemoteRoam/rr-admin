package tech.remote.admin.module.business.measurement.controller;

import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementTaskVO;
import tech.remote.admin.module.business.measurement.service.MeasurementTaskService;
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
 * 仪器计量实验室任务表 Controller
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class MeasurementTaskController {

    @Resource
    private MeasurementTaskService measurementTaskService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/measurementTask/queryPage")
    public ResponseDTO<PageResult<MeasurementTaskVO>> queryPage(@RequestBody @Valid MeasurementTaskQueryForm queryForm) {
        return ResponseDTO.ok(measurementTaskService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/measurementTask/add")
    public ResponseDTO<String> add(@RequestBody @Valid MeasurementTaskAddForm addForm) {
        return measurementTaskService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/measurementTask/update")
    public ResponseDTO<String> update(@RequestBody @Valid MeasurementTaskUpdateForm updateForm) {
        return measurementTaskService.update(updateForm);
    }

}
