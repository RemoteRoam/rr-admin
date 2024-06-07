package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 型号规格 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
public class SkusQueryForm extends PageParam{

    @Schema(description = "型号规格ID")
    private Integer skuId;

    @Schema(description = "商品ID")
    private Integer goodsId;

    @Schema(description = "商品类目ID")
    private Integer categoryId;

    @Schema(description = "型号规格名称")
    private String skuName;

}