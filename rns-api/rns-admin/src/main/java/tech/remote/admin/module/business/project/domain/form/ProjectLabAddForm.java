package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目实验室任务表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabAddForm {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "实验室任务编号")
    private String taskNo;

    @Schema(description = "三方机构ID（实验室ID）")
    private Long thirdPartyId;

    @Schema(description = "实验室合同号")
    private String labContractNo;

    @Schema(description = "实验室合同日期")
    private LocalDate labContractDate;

    @Schema(description = "实验费金额")
    private BigDecimal labContractAmount;

    @Schema(description = "客户要求完成日期")
    private LocalDate labExpectedDate;

    @Schema(description = "实验合同备注")
    private String labContractRemark;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}