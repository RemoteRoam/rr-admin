package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品
 *
 * @Author 远游工作室: 胡克
 * @Date 2021-10-25 20:26:54
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class GoodsVO  {

    @Schema(description = "商品分类")
    private Long categoryId;

    @Schema(description = "商品名称")
    private String goodsName;

//    @SchemaEnum(GoodsStatusEnum.class)
//    private Integer goodsStatus;

//    @Schema(description = "产地")
//    @JsonSerialize(using = DictValueVoSerializer.class)
//    private String place;
//
//    @Schema(description = "商品价格")
//    private BigDecimal price;
//
//    @Schema(description = "上架状态")
//    private Boolean shelvesFlag;

    @Schema(description = "备注|可选")
    private String remark;

    @Schema(description = "商品id")
    private Long goodsId;

    @Schema(description = "商品分类")
    private String categoryName;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    @Schema(description = "型号规格列表")
    private List<SkusVO> skuList;
}
