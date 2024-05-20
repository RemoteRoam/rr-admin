package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 项目邮寄表 更新表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectMailUpdateForm {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号 不能为空")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "邮寄日期")
    private LocalDate mailingDate;

    @Schema(description = "快递单号")
    private String trackingNumber;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}