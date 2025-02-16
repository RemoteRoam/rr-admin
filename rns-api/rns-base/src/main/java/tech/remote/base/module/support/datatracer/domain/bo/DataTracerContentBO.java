package tech.remote.base.module.support.datatracer.domain.bo;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * 变动内容
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class DataTracerContentBO {

    /**
     * 变动字段
     */
    private Field field;

    /**
     * 变动字段的值
     */
    private Object fieldValue;

    /**
     * 变动字段描述
     */
    private String fieldDesc;

    /**
     * 变动内容
     */
    private String fieldContent;

}
