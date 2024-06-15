package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售单表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SalesTemplateVO {

    @Schema(description = "销售公司")
    private String company;

    @Schema(description = "客户")
    private String customerName;

    @Schema(description = "合同号")
    private String contractNo;


    @Schema(description = "发货日期")
    private String shippingDate;

    @Schema(description = "发货总数量")
    private Integer quantityTotal = 0;

    @Schema(description = "发货总重量")
    private BigDecimal weightTotal = BigDecimal.ZERO;

}