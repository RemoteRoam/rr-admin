package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 库存表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryQueryForm extends PageParam{

    @Schema(description = "型号规格ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "商品类目ID")
    private Long categoryId;

}