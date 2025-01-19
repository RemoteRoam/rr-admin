package tech.remote.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 采购入库表 新建表单
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Data
public class PurchaseAddForm {

    @Schema(description = "采购单号")
    private String purchaseNo;

    @Schema(description = "供货厂家")
    private String supplier;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "采购单明细")
    private List<PurchaseItemAddForm> itemList;

}