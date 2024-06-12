package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 库存流水表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryFlowQueryForm extends PageParam{

    @Schema(description = "记录类型(1采购入库，2销售出库，3盘点)")
    private Integer type;

    @Schema(description = "对应单据表ID")
    private Long taskId;

    @Schema(description = "型号规格ID")
    private Integer skuId;

    @Schema(description = "商品ID")
    private Integer goodsId;

    @Schema(description = "商品类目ID")
    private Integer categoryId;

}