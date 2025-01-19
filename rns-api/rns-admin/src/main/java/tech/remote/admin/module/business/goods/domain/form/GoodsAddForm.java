package tech.remote.admin.module.business.goods.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.goods.constant.GoodsStatusEnum;
import tech.remote.base.common.json.deserializer.DictValueVoDeserializer;
import tech.remote.base.common.swagger.SchemaEnum;
import tech.remote.base.common.validator.enumeration.CheckEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品 添加表单
 *
 * @Author 远游工作室: 胡克
 * @Date 2021-10-25 20:26:54
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class GoodsAddForm {

    @Schema(description = "商品分类")
    @NotNull(message = "商品分类不能为空")
    private Long categoryId;

    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String goodsName;

    @Schema(description = "备注|可选")
    private String remark;

    @Schema(description = "SKU列表")
    private List<SkusAddForm> skuList;
}
