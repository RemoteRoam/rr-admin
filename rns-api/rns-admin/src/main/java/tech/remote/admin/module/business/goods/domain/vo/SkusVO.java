package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 型号规格 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
public class SkusVO {


    @Schema(description = "型号规格ID")
    private Integer skuId;

    @Schema(description = "商品ID")
    private Integer goodsId;

    @Schema(description = "商品类目ID")
    private Integer categoryId;

    @Schema(description = "型号规格名称")
    private String skuName;

    @Schema(description = "排的重量")
    private BigDecimal weight;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}