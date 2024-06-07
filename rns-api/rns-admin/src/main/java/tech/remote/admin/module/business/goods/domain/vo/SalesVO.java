package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 销售单表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesVO {


    @Schema(description = "销售单ID")
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

    @Schema(description = "备注")
    private String remark;

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