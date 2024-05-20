package tech.remote.admin.module.business.project.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 项目表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectQueryForm extends PageParam{

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "项目分类")
    private String category;

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

}