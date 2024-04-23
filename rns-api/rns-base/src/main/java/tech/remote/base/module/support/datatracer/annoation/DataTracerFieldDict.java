package tech.remote.base.module.support.datatracer.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典的字段
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTracerFieldDict {


}
