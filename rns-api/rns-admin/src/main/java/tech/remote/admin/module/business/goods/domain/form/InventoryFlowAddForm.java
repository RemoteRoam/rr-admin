package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 库存流水表 新建表单
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryFlowAddForm {

    @Schema(description = "库存流水ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存流水ID 不能为空")
    private Long id;

    @Schema(description = "记录类型(1采购入库，2销售出库，3盘点)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录类型(1采购入库，2销售出库，3盘点) 不能为空")
    private Integer type;

    @Schema(description = "对应单据表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对应单据表ID 不能为空")
    private Long taskId;

    @Schema(description = "型号规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "型号规格ID 不能为空")
    private Integer skuId;

    @Schema(description = "商品ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品ID 不能为空")
    private Integer goodsId;

    @Schema(description = "商品类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品类目ID 不能为空")
    private Integer categoryId;

    @Schema(description = "变更前SKU库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "变更前SKU库存数量 不能为空")
    private Integer oldQuantity;

    @Schema(description = "变更数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "变更数量 不能为空")
    private Integer changeQuantity;

}