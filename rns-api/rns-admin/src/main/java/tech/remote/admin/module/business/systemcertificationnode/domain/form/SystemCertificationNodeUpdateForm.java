package tech.remote.admin.module.business.systemcertificationnode.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 体系认证项目流程节点表 更新表单
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SystemCertificationNodeUpdateForm {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号 不能为空")
    private Long id;

    @Schema(description = "状态（NodeStatusEnum）")
    private Integer status;

    @Schema(description = "跳过原因")
    private String passReason;

    @Schema(description = "操作人")
    private Long operateUserId;

    @Schema(description = "操作人姓名")
    private String operateUserName;

    @Schema(description = "操作时间")
    private LocalDateTime operateTime;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}