package tech.remote.base.module.support.datatracer.annoation;

import tech.remote.base.common.enumeration.BaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段枚举
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTracerFieldEnum {

    Class<? extends BaseEnum> enumClass() default BaseEnum.class;

}
