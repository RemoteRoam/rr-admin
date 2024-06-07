package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 库存表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_inventory")
public class InventoryEntity {

    /**
     * 库存ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 型号规格ID
     */
    private Integer skuId;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品类目ID
     */
    private Integer categoryId;

    /**
     * SKU库存数量
     */
    private Integer quantity;

    /**
     * 删除状态
     */
    private Integer deletedFlag;

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