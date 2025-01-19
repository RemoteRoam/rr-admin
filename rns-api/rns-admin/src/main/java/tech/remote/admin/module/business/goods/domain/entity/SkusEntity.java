package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 型号规格 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_skus")
public class SkusEntity {

    /**
     * 型号规格ID
     */
    @TableId(type = IdType.AUTO)
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
     * 型号规格名称
     */
    private String skuName;

    /**
     * 长度
     */
    private String length;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * SKU库存数量
     */
    private Integer stockQuantity;

    /**
     * 上架状态
     */
    private Integer shelvesFlag;

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