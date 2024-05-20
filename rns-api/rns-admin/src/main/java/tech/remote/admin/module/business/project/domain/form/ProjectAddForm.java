package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectAddForm {

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

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同日")
    private LocalDate contractDate;

    @Schema(description = "合同金额")
    private BigDecimal contractAmount;

    @Schema(description = "客户预期日期")
    private LocalDate expectedDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}