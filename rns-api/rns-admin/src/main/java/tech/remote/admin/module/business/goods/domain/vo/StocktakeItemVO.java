package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 盘点明细表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class StocktakeItemVO {


    @Schema(description = "盘点明细ID")
    private Long id;

    @Schema(description = "盘点单ID")
    private Long stocktakeId;

    @Schema(description = "型号规格ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "商品类目ID")
    private Long categoryId;

    @Schema(description = "盘点数量")
    private Integer quantity;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "商品名称")
    private String goodsName;

    @Schema(description = "商品分类")
    private String categoryName;

    @Schema(description = "型号规格名称")
    private String skuName;

    @Schema(description = "重量")
    private BigDecimal weight;

}