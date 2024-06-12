package tech.remote.base.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordTypeEnum implements BaseEnum {

    /**
     * 采购入库
     */
    PURCHASE_INBOUND(1, "采购入库"),

    /**
     * 销售出库
     */
    SALES_OUTBOUND(2, "销售出库"),

    /**
     * 盘点
     */
    INVENTORY_CHECK(3, "盘点"),
    ;

    private final Integer value;

    private final String desc;

}