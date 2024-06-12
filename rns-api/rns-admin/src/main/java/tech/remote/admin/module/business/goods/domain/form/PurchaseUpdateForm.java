package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 采购入库表 更新表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Data
public class PurchaseUpdateForm {

    @Schema(description = "采购入库ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采购入库ID 不能为空")
    private Long id;

    @Schema(description = "采购单号")
    private String purchaseNo;

    @Schema(description = "供货厂家")
    private String supplier;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}