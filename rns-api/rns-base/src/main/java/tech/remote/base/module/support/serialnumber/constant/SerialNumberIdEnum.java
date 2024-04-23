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
