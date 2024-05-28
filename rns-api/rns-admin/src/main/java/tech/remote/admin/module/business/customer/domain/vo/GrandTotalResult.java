package tech.remote.admin.module.business.customer.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GrandTotalResult {
    private BigDecimal grandTotalAmount;
    private Integer grandTotalCount;
}
