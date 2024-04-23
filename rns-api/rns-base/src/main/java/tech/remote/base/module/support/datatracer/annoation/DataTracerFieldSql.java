package tech.remote.base.module.support.datatracer.annoation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 支持查询sql
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTracerFieldSql {

    /**
     * 关联字段名称
     * @return
     */
    String relateColumn() default "id";

    /**
     * 关联显示的字段
     * @return
     */
    String relateDisplayColumn() default "";
    /**
     * 是否关联字段查询Mapper
     * @return
     */
    Class<? extends BaseMapper> relateMapper() default BaseMapper.class;

}
