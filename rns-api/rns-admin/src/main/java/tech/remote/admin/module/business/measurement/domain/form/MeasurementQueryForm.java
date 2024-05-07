package tech.remote.admin.module.business.measurement.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 仪器计量表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementQueryForm extends PageParam{

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "负责人ID")
    private Long managerId;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

}