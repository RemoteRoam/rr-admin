package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目产品表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectProductVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "实验室任务ID")
    private Long taskId;

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品型号")
    private String productModel;

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
    private Long mail;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}