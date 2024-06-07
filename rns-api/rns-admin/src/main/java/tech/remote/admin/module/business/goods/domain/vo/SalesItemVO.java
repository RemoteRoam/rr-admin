package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 销售明细表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesItemVO {


    @Schema(description = "销售明细ID")
    private Long id;

    @Schema(description = "销售单ID")
    private Long salesId;

    @Schema(description = "型号规格ID")
    private Integer skuId;

    @Schema(description = "商品ID")
    private Integer goodsId;

    @Schema(description = "商品类目ID")
    private Integer categoryId;

    @Schema(description = "销售数量")
    private Integer quantity;

    @Schema(description = "销售单价")
    private BigDecimal unitPrice;

    @Schema(description = "备注")
    private String remark;

}