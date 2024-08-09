package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 项目表 更新表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectUpdateForm {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号 不能为空")
    private Long id;

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "项目分类")
    private Integer category;

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

    @Schema(description = "首款金额")
    private BigDecimal firstPaymentAmount;

    @Schema(description = "首款收款日期")
    private LocalDate firstPaymentDate;

    @Schema(description = "审核任务时间")
    private LocalDate auditTaskDate;

    @Schema(description = "审核老师")
    private String auditTeacher;

    @Schema(description = "咨询老师")
    private String consultationTeacher;

    @Schema(description = "审核日期")
    private LocalDate auditDate;

    @Schema(description = "整改内容")
    private String rectificationContent;

    @Schema(description = "整改日期")
    private LocalDate rectificationDate;

    @Schema(description = "抽检实验完成日期")
    private LocalDate samplingTestDate;

    @Schema(description = "是否付款")
    private Integer isPaid;

    @Schema(description = "付款方(客户/我方)")
    private Integer payParty;

    @Schema(description = "认证费付款日期")
    private LocalDate payDate;

    @Schema(description = "认证费备注")
    private String payRemark;

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

    @Schema(description = "归档位置")
    private String archivePosition;

    @Schema(description = "归档日期")
    private LocalDate archiveDate;

    @Schema(description = "邮寄日期")
    private LocalDate mailingDate;

    @Schema(description = "快递单号")
    private String trackingNumber;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "产品监督证书到期日")
    private LocalDate certificateExpiryDate;

    @Schema(description = "上年度审核日期")
    private LocalDate lastYearAuditDate;

    @Schema(description = "项目节点ID")
    private Long projectNodeId;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "状态（NodeStatusEnum）")
    private Integer nodeStatus;

    @Schema(description = "跳过原因")
    private String passReason;

    @Schema(description = "产品ID列表")
    private List<Long> productIdList;
}