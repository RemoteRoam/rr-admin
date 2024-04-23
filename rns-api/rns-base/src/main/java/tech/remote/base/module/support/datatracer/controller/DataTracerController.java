package tech.remote.base.module.support.datatracer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.datatracer.domain.form.DataTracerQueryForm;
import tech.remote.base.module.support.datatracer.domain.vo.DataTracerVO;
import tech.remote.base.module.support.datatracer.service.DataTracerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 数据变动记录
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Tag(name = SwaggerTagConst.Support.DATA_TRACER)
@RestController
public class DataTracerController extends SupportBaseController {

    @Resource
    private DataTracerService dataTracerService;

    @Operation(summary = "分页查询业务操作日志 - @author 卓大")
    @PostMapping("/dataTracer/query")
    public ResponseDTO<PageResult<DataTracerVO>> query(@Valid @RequestBody DataTracerQueryForm queryForm) {
        return dataTracerService.query(queryForm);
    }
}
