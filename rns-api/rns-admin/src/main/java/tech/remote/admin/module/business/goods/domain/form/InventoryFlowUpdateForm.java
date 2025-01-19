package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 库存流水表 更新表单
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryFlowUpdateForm {

    @Schema(description = "库存流水ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存流水ID 不能为空")
    private Long id;

}