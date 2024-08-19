package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.base.common.domain.PageResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目试验室任务表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabTodoListVO {

    @Schema(description = "预计完成待办数量")
    Long estimateCompletionCount;

    @Schema(description = "试验检测待办数量")
    Long experimentCheckCount;

    @Schema(description = "试验室上报待办数量")
    Long labReportCount;

    @Schema(description = "预计完成待办列表")
    PageResult<ProjectProductListVO> estimateCompletionList;

    @Schema(description = "试验检测待办列表")
    PageResult<ProjectProductListVO> experimentCheckList;

    @Schema(description = "试验室上报待办列表")
    PageResult<ProjectProductListVO> labReportList;
}