package tech.remote.admin.module.business.measurement.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 仪器计量表 更新表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementUpdateForm {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号 不能为空")
    private Long id;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "负责人ID")
    private Long managerId;

    @Schema(description = "收款金额")
    private BigDecimal paymentAmount;

    @Schema(description = "收款日期")
    private LocalDate paymentDate;

    @Schema(description = "备注")
    private String remark;

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

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}