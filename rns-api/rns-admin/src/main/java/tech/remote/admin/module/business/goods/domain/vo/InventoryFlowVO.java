package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 库存流水表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryFlowVO {


    @Schema(description = "库存流水ID")
    private Long id;

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

    @Schema(description = "变更前SKU库存数量")
    private Integer oldQuantity;

    @Schema(description = "变更数量")
    private Integer changeQuantity;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}