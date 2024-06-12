package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 销售单表 更新表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesUpdateForm {

    @Schema(description = "销售单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "销售单ID 不能为空")
    private Long id;

    @Schema(description = "销售单号")
    private String salesNo;

    @Schema(description = "销售类型（0:库存，1:厂家）")
    private Integer salesType;

    @Schema(description = "供货厂家")
    private String supplier;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "销售经理ID")
    private Long managerId;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同日")
    private LocalDate contractDate;

    @Schema(description = "合同金额")
    private BigDecimal contractAmount;

    @Schema(description = "回款日期")
    private LocalDate receivedPaymentDate;

    @Schema(description = "回款金额")
    private BigDecimal receivedPaymentAmount;

    @Schema(description = "发货日期")
    private LocalDate shippingDate;

    @Schema(description = "发货金额")
    private BigDecimal shippingAmount;

    @Schema(description = "开票日期")
    private LocalDate invoiceDate;

    @Schema(description = "发票金额")
    private BigDecimal invoiceAmount;

    @Schema(description = "发票号")
    private String invoiceNumber;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

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