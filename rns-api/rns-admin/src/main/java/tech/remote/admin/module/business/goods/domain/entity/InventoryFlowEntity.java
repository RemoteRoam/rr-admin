package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 库存流水表 实体类
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_inventory_flow")
public class InventoryFlowEntity {

    /**
     * 库存流水ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 记录类型(1采购入库，2销售出库，3盘点)
     */
    private Integer type;

    /**
     * 对应单据表ID
     */
    private Long taskId;

    /**
     * 型号规格ID
     */
    private Long skuId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品类目ID
     */
    private Long categoryId;

    /**
     * 变更前SKU库存数量
     */
    private Integer oldQuantity;

    /**
     * 变更数量
     */
    private Integer changeQuantity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}