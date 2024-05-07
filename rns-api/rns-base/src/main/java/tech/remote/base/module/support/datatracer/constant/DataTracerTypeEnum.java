package tech.remote.base.module.support.datatracer.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.remote.base.common.enumeration.BaseEnum;

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

    ;

    private final Integer value;

    private final String desc;
}
