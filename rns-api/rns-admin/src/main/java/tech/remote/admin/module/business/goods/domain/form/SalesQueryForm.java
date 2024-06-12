package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 销售单表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesQueryForm extends PageParam{

    @Schema(description = "销售单号")
    private String salesNo;

    @Schema(description = "销售类型")
    private Integer salesType;

    @Schema(description = "供货厂家")
    private String supplier;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "销售经理ID")
    private Long managerId;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同日")
    private LocalDate contractDateBegin;

    @Schema(description = "合同日")
    private LocalDate contractDateEnd;

}