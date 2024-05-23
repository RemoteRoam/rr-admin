package tech.remote.admin.module.business.measurement.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementExcelVO;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.admin.module.business.measurement.service.MeasurementService;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.common.util.SmartResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 仪器计量表 Controller
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = AdminSwaggerTagConst.ProjectManagement.MEASUREMENT)
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
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return measurementService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/measurement/update")
    public ResponseDTO<String> update(@RequestBody @Valid MeasurementUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
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

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/measurement/get/{id}")
    public ResponseDTO<MeasurementVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(measurementService.getDetail(id));
    }

    @Operation(summary = "导出 @author cbh")
    @PostMapping("/measurement/exportExcel")
    public void exportExcel(@RequestBody @Valid MeasurementQueryForm queryForm, HttpServletResponse response) throws IOException {
        List<MeasurementExcelVO> data = measurementService.getExcelExportData(queryForm);
        if (CollectionUtils.isEmpty(data)) {
            SmartResponseUtil.write(response, ResponseDTO.userErrorParam("暂无数据"));
            return;
        }

        String fileName = "仪器计量-" + DateUtil.today() + ".xls";

        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, fileName, null);

        // 下载
        EasyExcel.write(response.getOutputStream(), MeasurementExcelVO.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("仪器计量")
                .doWrite(data);
    }
}
