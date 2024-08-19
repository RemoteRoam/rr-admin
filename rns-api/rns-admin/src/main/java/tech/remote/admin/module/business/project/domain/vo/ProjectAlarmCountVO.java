package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 项目预警数量VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectAlarmCountVO {

    @Schema(description = "客户合同预期完成时间预警")
    private Integer expectedDateAlarm;

    @Schema(description = "试验室任务预警")
    private Integer labExpectedDateAlarm;

    @Schema(description = "工厂审核监督预警")
    private Integer auditDateAlarm;

    @Schema(description = "不符合项整改预警")
    private Integer nonConformityCorrectionAlarm;

    @Schema(description = "体系审核到期预警")
    private Integer systemCertificationAuditDateAlarm;

    @Schema(description = "证书有效期预警")
    private Integer certificateExpiryDateAlarm;

    @Schema(description = "仪器计量预警")
    private Integer measurementDateAlarm;

}