package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 盘点明细表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_stocktake_item")
public class StocktakeItemEntity {

    /**
     * 盘点明细ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 盘点单ID
     */
    private Long stocktakeId;

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
     * 盘点数量
     */
    private Integer quantity;

    /**
     * 备注
     */
    private String remark;

}