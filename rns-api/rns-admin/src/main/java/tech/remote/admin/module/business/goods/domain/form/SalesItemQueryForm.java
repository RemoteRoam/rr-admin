package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 销售明细表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesItemQueryForm extends PageParam{

    @Schema(description = "销售单ID")
    private Long salesId;

    @Schema(description = "型号规格ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "商品类目ID")
    private Long categoryId;

}