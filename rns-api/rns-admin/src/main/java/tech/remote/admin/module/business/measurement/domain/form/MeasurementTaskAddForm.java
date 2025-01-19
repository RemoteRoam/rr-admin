package tech.remote.admin.module.business.measurement.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 仪器计量试验室任务表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementTaskAddForm {

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "三方机构ID（试验室ID）")
    private Long thirdPartyId;

    @Schema(description = "仪器名称")
    private String instrument;

    @Schema(description = "有效期")
    private LocalDate expiryDate;

    @Schema(description = "试验费金额")
    private BigDecimal experimentAmount;

    @Schema(description = "成本价")
    private BigDecimal costAmount;

    @Schema(description = "计量日期")
    private LocalDate measurementDate;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}