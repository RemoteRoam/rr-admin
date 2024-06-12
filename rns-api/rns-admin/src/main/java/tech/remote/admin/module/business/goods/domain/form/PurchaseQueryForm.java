package tech.remote.admin.module.business.goods.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 采购入库表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Data
public class PurchaseQueryForm extends PageParam{

    @Schema(description = "采购单号")
    private String purchaseNo;

    @Schema(description = "供货厂家")
    private String supplier;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

}