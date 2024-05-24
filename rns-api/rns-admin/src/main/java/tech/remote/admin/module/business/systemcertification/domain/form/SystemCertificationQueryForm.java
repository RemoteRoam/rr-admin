package tech.remote.admin.module.business.systemcertification.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 体系认证表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Data
public class SystemCertificationQueryForm extends PageParam{

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "项目分类")
    private String category;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "来源分类")
    private Integer sourceType;

    @Schema(description = "来源ID")
    private Long sourceId;

    @Schema(description = "认证机构ID")
    private Long thirdPartyId;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同日")
    private LocalDate contractDateBegin;

    @Schema(description = "合同日")
    private LocalDate contractDateEnd;

    @Schema(description = "客户预期日期")
    private LocalDate expectedDateBegin;

    @Schema(description = "客户预期日期")
    private LocalDate expectedDateEnd;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

    @Schema(description = "预警类型 5:体系审核到期预警 6:证书有效期预警")
    private Integer alarmType;
}