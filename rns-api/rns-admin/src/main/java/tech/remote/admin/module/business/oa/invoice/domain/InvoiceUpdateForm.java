package tech.remote.admin.module.business.oa.invoice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * OA发票信息编辑
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-06-23 19:32:59
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class InvoiceUpdateForm extends InvoiceAddForm {

    @Schema(description = "发票信息ID")
    @NotNull(message = "发票信息ID不能为空")
    private Long invoiceId;
}
