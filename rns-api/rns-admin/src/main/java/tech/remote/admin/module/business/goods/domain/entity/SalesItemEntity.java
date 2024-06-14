package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldBigDecimal;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 销售明细表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_sales_item")
public class SalesItemEntity {

    /**
     * 销售明细ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 销售单ID
     */
    private Long salesId;

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
     * 销售数量
     */
    @DataTracerFieldLabel("销售数量")
    private Integer quantity;

    /**
     * 销售单价
     */
    @DataTracerFieldLabel("销售单价")
    @DataTracerFieldBigDecimal
    private BigDecimal unitPrice;

    /**
     * 重量
     */
    @DataTracerFieldLabel("重量")
    @DataTracerFieldBigDecimal
    private BigDecimal weight;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    private String remark;

}