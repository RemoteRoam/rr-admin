package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 项目产品表 更新表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectProductUpdateForm {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号 不能为空")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "实验室任务ID")
    private Long taskId;

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品型号")
    private String productModel;

    @Schema(description = "预计完成日期")
    private LocalDate expectedCompletionDate;

    @Schema(description = "报告完成日期")
    private LocalDate reportCompletionDate;

    @Schema(description = "实验室上报日期")
    private LocalDate labReportDate;

    @Schema(description = "自我声明日期")
    private LocalDate selfDeclarationDate;

    @Schema(description = "证书编号")
    private String certificateNo;

    @Schema(description = "证书发送日期")
    private LocalDate certificateSendDate;

    @Schema(description = "证书有效期截止日期")
    private LocalDate certificateExpiryDate;

    @Schema(description = "项目认证费表ID")
    private Long certificationFeeId;

    @Schema(description = "项目归档表ID")
    private Long archiveId;

    @Schema(description = "项目邮寄表ID")
    private Long mailId;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "项目节点ID")
    private Long projectNodeId;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "状态（NodeStatusEnum）")
    private Integer nodeStatus;

    @Schema(description = "跳过原因")
    private String passReason;
}