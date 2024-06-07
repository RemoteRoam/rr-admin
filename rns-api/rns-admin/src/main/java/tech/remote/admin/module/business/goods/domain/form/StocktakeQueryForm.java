package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 盘点表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class StocktakeQueryForm extends PageParam{

    @Schema(description = "盘点单号")
    private String stocktakeNo;

    @Schema(description = "盘点时间")
    private LocalDateTime createTime;

}