package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 采购入库明细表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class PurchaseItemQueryForm extends PageParam{

    @Schema(description = "采购入库单ID")
    private Long purchaseId;

    @Schema(description = "型号规格ID")
    private Integer skuId;

    @Schema(description = "商品ID")
    private Integer goodsId;

    @Schema(description = "商品类目ID")
    private Integer categoryId;

}