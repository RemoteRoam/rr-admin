package tech.remote.admin.module.business.project.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.project.domain.form.ProjectAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectToDoQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectExcelVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
import tech.remote.admin.module.business.project.service.ProjectService;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
 * 项目表 Controller
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/project/queryPage")
    public ResponseDTO<PageResult<ProjectVO>> queryPage(@RequestBody @Valid ProjectQueryForm queryForm) {
        return ResponseDTO.ok(projectService.queryPage(queryForm));
    }

    @Operation(summary = "待办列表分页查询 @author cbh")
    @PostMapping("/project/queryToDoPage")
    public ResponseDTO<PageResult<ProjectVO>> queryToDoPage(@RequestBody @Valid ProjectToDoQueryForm queryForm) {
        return ResponseDTO.ok(projectService.queryToDoPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/project/add")
    public ResponseDTO<String> add(@RequestBody @Valid ProjectAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return projectService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/project/update")
    public ResponseDTO<String> update(@RequestBody @Valid ProjectUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return projectService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/project/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return projectService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/project/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return projectService.delete(id);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/project/get/{id}")
    public ResponseDTO<ProjectVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(projectService.getDetail(id));
    }

    @Operation(summary = "查询所有产品列表 @author cbh")
    @GetMapping("/project/getAllProduct/{id}")
    public ResponseDTO<List<ProjectProductVO>> getAllProduct(@PathVariable Long id) {
        return ResponseDTO.ok(projectService.getAllProduct(id));
    }

    @Operation(summary = "查询待处理的产品列表 @author cbh")
    @GetMapping("/project/getPendingProduct/{id}/{nodeId}")
    public ResponseDTO<List<ProjectProductVO>> getPendingProduct(@PathVariable Long id, @PathVariable Integer nodeId) {
        return ResponseDTO.ok(projectService.getPendingProduct(id, nodeId));
    }

    @Operation(summary = "导出 @author cbh")
    @PostMapping("/project/exportExcel")
    public void exportExcel(@RequestBody @Valid ProjectQueryForm queryForm, HttpServletResponse response) throws IOException {
        List<ProjectExcelVO> data = projectService.getExcelExportData(queryForm);
        if (CollectionUtils.isEmpty(data)) {
            SmartResponseUtil.write(response, ResponseDTO.userErrorParam("暂无数据"));
            return;
        }

        String projectTypeName = ProjectTypeEnum.getDescByValue(queryForm.getProjectType());
        String fileName = projectTypeName + "-" + DateUtil.today() + ".xls";

        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, fileName, null);

        // 下载
        EasyExcel.write(response.getOutputStream(), ProjectExcelVO.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet(projectTypeName)
                .doWrite(data);
    }
}
