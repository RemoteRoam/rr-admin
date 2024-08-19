package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;

import java.time.LocalDate;
import java.util.List;

/**
 * 项目表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectToDoQueryForm extends PageParam{

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目分类")
    private Integer category;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "来源分类")
    private Integer sourceType;

    @Schema(description = "来源ID")
    private Long sourceId;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

    @Schema(description = "待办类型：1 办公室待办，2 试验室待办，3 审核待办，4 取证书待办，5 尾款待办")
    private Integer toDoType;


}