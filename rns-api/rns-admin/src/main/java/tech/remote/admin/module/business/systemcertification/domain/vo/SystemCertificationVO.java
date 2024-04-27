package tech.remote.admin.module.business.systemcertification.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 体系认证表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Data
public class SystemCertificationVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private String projectType;

    @Schema(description = "项目分类")
    private String category;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "客户")
    private String customerName;

    @Schema(description = "来源分类")
    private Integer sourceType;

    @Schema(description = "来源ID")
    private Long sourceId;

    @Schema(description = "来源")
    private String sourceName;

    @Schema(description = "认证机构ID")
    private Long thirdPartyId;

    @Schema(description = "认证机构")
    private String thirdPartyName;

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

    @Schema(description = "首款金额")
    private BigDecimal firstPaymentAmount;

    @Schema(description = "首款收款日期")
    private LocalDate firstPaymentDate;

    @Schema(description = "资料上报日期")
    private LocalDate dataReportDate;

    @Schema(description = "审核老师")
    private String auditTeacher;

    @Schema(description = "审核日期")
    private LocalDate auditDate;

    @Schema(description = "咨询老师")
    private String consultationTeacher;

    @Schema(description = "交卷日期")
    private LocalDate submissionDate;

    @Schema(description = "评定日期")
    private LocalDate assessmentDate;

    @Schema(description = "整改日期")
    private LocalDate rectificationDate;

    @Schema(description = "证书发送日期")
    private LocalDate certificateSendDate;

    @Schema(description = "证书有效期截止日期")
    private LocalDate certificateExpiryDate;

    @Schema(description = "尾款金额")
    private BigDecimal finalPaymentAmount;

    @Schema(description = "尾款收款日期")
    private LocalDate finalPaymentDate;

    @Schema(description = "开票日期")
    private LocalDate invoiceDate;

    @Schema(description = "发票金额")
    private BigDecimal invoiceAmount;

    @Schema(description = "发票号")
    private String invoiceNumber;

    @Schema(description = "邮寄日期")
    private LocalDate mailingDate;

    @Schema(description = "快递单号")
    private String trackingNumber;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}