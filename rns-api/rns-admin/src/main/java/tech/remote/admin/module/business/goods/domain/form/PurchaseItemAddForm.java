package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 采购入库明细表 新建表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class PurchaseItemAddForm {

    @Schema(description = "采购入库单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采购入库单ID 不能为空")
    private Long purchaseId;

    @Schema(description = "型号规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "型号规格ID 不能为空")
    private Long skuId;

    @Schema(description = "商品ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品ID 不能为空")
    private Long goodsId;

    @Schema(description = "商品类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品类目ID 不能为空")
    private Long categoryId;

    @Schema(description = "采购数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采购数量 不能为空")
    private Integer quantity;

    @Schema(description = "采购单价")
    private BigDecimal unitPrice;

    @Schema(description = "重量")
    private BigDecimal weight;

}