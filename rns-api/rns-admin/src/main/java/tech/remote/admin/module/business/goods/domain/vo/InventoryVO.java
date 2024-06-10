package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 库存表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class InventoryVO {


    @Schema(description = "库存ID")
    private Integer id;

    @Schema(description = "型号规格ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "商品类目ID")
    private Long categoryId;

    @Schema(description = "SKU库存数量")
    private Integer quantity;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}