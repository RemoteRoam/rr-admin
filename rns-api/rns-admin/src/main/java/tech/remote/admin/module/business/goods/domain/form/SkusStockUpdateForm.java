package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * SKU库存 更新表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
public class SkusStockUpdateForm {

    @Schema(description = "型号规格ID")
    private Long skuId;

    @Schema(description = "数量，可以为负数，表示减少库存")
    private Integer quantity;

    @Schema(description = "重量")
    private BigDecimal weight = BigDecimal.ZERO;

}