package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 库存表 新建表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryAddForm {

    @Schema(description = "型号规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "型号规格ID 不能为空")
    private Long skuId;

    @Schema(description = "商品ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品ID 不能为空")
    private Long goodsId;

    @Schema(description = "商品类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品类目ID 不能为空")
    private Long categoryId;

    @Schema(description = "SKU库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "SKU库存数量 不能为空")
    private Integer quantity;

    @Schema(description = "删除状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "删除状态 不能为空")
    private Integer deletedFlag;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}