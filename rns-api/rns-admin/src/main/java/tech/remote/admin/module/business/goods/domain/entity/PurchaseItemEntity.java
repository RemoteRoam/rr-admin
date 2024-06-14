package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 采购入库明细表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_purchase_item")
public class PurchaseItemEntity {

    /**
     * 采购入库明细ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 采购入库单ID
     */
    private Long purchaseId;

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
     * 采购数量
     */
    private Integer quantity;

    /**
     * 采购单价
     */
    private BigDecimal unitPrice;

    /**
     * 重量
     */
    private BigDecimal weight;
}