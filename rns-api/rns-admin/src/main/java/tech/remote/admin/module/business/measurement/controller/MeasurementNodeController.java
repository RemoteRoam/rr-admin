package tech.remote.admin.module.business.measurement.controller;

import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import tech.remote.admin.module.business.measurement.service.MeasurementNodeService;
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
 * 仪器计量流程节点表 Controller
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class MeasurementNodeController {

    @Resource
    private MeasurementNodeService measurementNodeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/measurementNode/queryPage")
    public ResponseDTO<PageResult<MeasurementNodeVO>> queryPage(@RequestBody @Valid MeasurementNodeQueryForm queryForm) {
        return ResponseDTO.ok(measurementNodeService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/measurementNode/add")
    public ResponseDTO<String> add(@RequestBody @Valid MeasurementNodeAddForm addForm) {
        return measurementNodeService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/measurementNode/update")
    public ResponseDTO<String> update(@RequestBody @Valid MeasurementNodeUpdateForm updateForm) {
        return measurementNodeService.update(updateForm);
    }

}
