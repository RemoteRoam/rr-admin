package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 销售明细表 更新表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesItemUpdateForm {

    @Schema(description = "销售明细ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "销售明细ID 不能为空")
    private Long id;

    @Schema(description = "销售单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "销售单ID 不能为空")
    private Long salesId;

    @Schema(description = "型号规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "型号规格ID 不能为空")
    private Integer skuId;

    @Schema(description = "商品ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品ID 不能为空")
    private Integer goodsId;

    @Schema(description = "商品类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品类目ID 不能为空")
    private Integer categoryId;

    @Schema(description = "销售数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "销售数量 不能为空")
    private Integer quantity;

    @Schema(description = "销售单价", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "销售单价 不能为空")
    private BigDecimal unitPrice;

    @Schema(description = "备注")
    private String remark;

}