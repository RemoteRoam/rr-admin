package tech.remote.admin.module.business.project.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tech.remote.admin.module.business.project.domain.form.*;
import tech.remote.admin.module.business.project.domain.vo.*;
import tech.remote.admin.module.business.project.service.ProjectLabService;
import tech.remote.base.common.annoation.NoNeedLogin;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.enumeration.ProjectTypeEnum;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.common.util.SmartResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 项目实验室任务表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectLabController {

    @Resource
    private ProjectLabService projectLabService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectLab/queryPage")
    public ResponseDTO<PageResult<ProjectLabVO>> queryPage(@RequestBody @Valid ProjectLabQueryForm queryForm) {
        return ResponseDTO.ok(projectLabService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/projectLab/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectLabAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return projectLabService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/projectLab/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectLabUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return projectLabService.update(updateForm);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/projectLab/get/{id}")
    public ResponseDTO<ProjectLabVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(projectLabService.getDetail(id));
    }

    @NoNeedLogin
    @Operation(summary = "获取项目进度 @author cbh")
    @GetMapping("/project/getProgress/{progressCode}")
    public ResponseDTO<ProjectLabProgressVO> getProgress(@PathVariable String progressCode) {
        return ResponseDTO.ok(projectLabService.getProgress(progressCode));
    }

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/projectLab/queryLabListPage")
    public ResponseDTO<PageResult<ProjectLabListVO>> queryLabListPage(@RequestBody @Valid ProjectLabListQueryForm queryForm) {
        return ResponseDTO.ok(projectLabService.getProjectLabs(queryForm));
    }

    @Operation(summary = "导出 @author cbh")
    @PostMapping("/projectLab/exportExcel")
    public void exportExcel(@RequestBody @Valid ProjectLabListQueryForm queryForm, HttpServletResponse response) throws IOException {
        List<ProjectLabExcelVO> data = projectLabService.getExcelExportData(queryForm);
        if (CollectionUtils.isEmpty(data)) {
            SmartResponseUtil.write(response, ResponseDTO.userErrorParam("暂无数据"));
            return;
        }

        String projectTypeName = "实验室任务";
        String fileName = projectTypeName + "-" + DateUtil.today() + ".xls";

        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, fileName, null);

        // 下载
        EasyExcel.write(response.getOutputStream(), ProjectLabExcelVO.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet(projectTypeName)
                .doWrite(data);
    }
}
