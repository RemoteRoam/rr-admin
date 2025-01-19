package tech.remote.base.module.support.serialnumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 单据序列号 枚举
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@AllArgsConstructor
@Getter
public enum SerialNumberIdEnum implements BaseEnum {

    ORDER(1, "订单id"),

    CONTRACT(2, "合同id"),
    PROJECT(3, "产品项目编号"),
    SYSTEM_CERTIFICATION(4, "体系认证项目编号"),
    MEASUREMENT(5, "仪器计量项目编号"),
    MEASUREMENT_TASK(6, "仪器计量项目试验室任务编号"),
    PROJECT_TASK(7, "产品项目试验室任务编号"),
    PURCHASE_NO(8, "采购单号"),
    SALES_NO(9, "销售单号"),
    STOCKTAKE_NO(10, "盘点单号"),

    ;

    private final Integer serialNumberId;

    private final String desc;

    @Override
    public Integer getValue() {
        return serialNumberId;
    }

    @Override
    public String toString() {
        return "SerialNumberIdEnum{" +
                "serialNumberId=" + serialNumberId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
