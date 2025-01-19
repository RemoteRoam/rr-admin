package tech.remote.base.module.support.datatracer.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.remote.base.common.enumeration.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据业务类型
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52-
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@AllArgsConstructor
@Getter
public enum DataTracerTypeEnum implements BaseEnum {

    /**
     * 商品
     */
    GOODS(1, "商品"),

    /**
     *通知公告
     */
    OA_NOTICE(2, "OA-通知公告"),

    /**
     * 企业信息
     */
    OA_ENTERPRISE(3, "OA-企业信息"),
    /**
     * 体系认证
     */
    SYSTEM_CERTIFICATION(4, "体系认证"),
    /**
     * 仪器计量
     */
    MEASUREMENT(5, "仪器计量"),
    /**
     * 仪器计量试验室任务
     */
    MEASUREMENT_TASK(6, "仪器计量试验室任务"),
    /**
     * 产品认证试验室任务
     */
    PROJECT_LAB_TASK(111, "项目试验室任务"),
    PROJECT_PRODUCT(112, "项目产品"),
    PC_CCC(11, "CCC自我声明"),
    PC_CERTIFICATION(12, "产品认证"),
    LAB_PROJECT(21, "试验项目"),
    PC_SUPERVISION(31, "产品监督"),
    SALES(61, "销售"),

    ;

    private final Integer value;

    private final String desc;
    private static final Map<Integer, DataTracerTypeEnum> enumMap = new HashMap<>();

    static {
        for (DataTracerTypeEnum enumType : DataTracerTypeEnum.values()) {
            enumMap.put(enumType.getValue(), enumType);
        }
    }

    /**
     * 通过value获取对应的枚举值
     *
     * @param value 枚举值
     * @return 对应的枚举对象
     */
    public static DataTracerTypeEnum fromValue(Integer value) {
        return enumMap.get(value);
    }
}
