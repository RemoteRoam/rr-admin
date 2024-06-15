package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 销售明细表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesTemplateItemVO {


    @Schema(description = "序号")
    private Integer no;

    @Schema(description = "商品名称")
    private String goodsName;

    @Schema(description = "商品分类")
    private String categoryName;

    @Schema(description = "型号规格名称")
    private String skuName;

    @Schema(description = "重量")
    private BigDecimal weight;

    @Schema(description = "销售数量")
    private Integer quantity;

    @Schema(description = "销售单价")
    private BigDecimal unitPrice;

    @Schema(description = "备注")
    private String remark;

}