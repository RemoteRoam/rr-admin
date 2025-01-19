package tech.remote.base.module.support.operatelog.annotation;

import java.lang.annotation.*;

/**
 * 用户操作日志 注解
 *
 * @Author 远游工作室: 罗伊
 * @Date 2021-12-08 20:48:52
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface OperateLog {

}
