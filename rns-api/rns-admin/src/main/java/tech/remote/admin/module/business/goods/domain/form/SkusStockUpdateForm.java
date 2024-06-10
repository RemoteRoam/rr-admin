package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 型号规格 更新表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
public class SkusInventoryUpdateForm {

    @Schema(description = "型号规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "型号规格ID 不能为空")
    private Long skuId;

    @Schema(description = "商品ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品ID 不能为空")
    private Long goodsId;

    @Schema(description = "商品类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品类目ID 不能为空")
    private Long categoryId;

    @Schema(description = "型号规格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "型号规格名称 不能为空")
    private String skuName;

    @Schema(description = "排的重量")
    private BigDecimal weight;

    @Schema(description = "上架状态")
    private Integer shelvesFlag;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}