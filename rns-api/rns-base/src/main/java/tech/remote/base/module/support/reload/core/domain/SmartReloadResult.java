package tech.remote.base.module.support.reload.core.domain;

import lombok.Data;

/**
 * t_reload_result 表 实体类
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class SmartReloadResult {

    /**
     * 项名称
     */
    private String tag;

    /**
     * 参数
     */
    private String args;

    /**
     * 标识
     */
    private String identification;

    /**
     * 处理结果
     */
    private boolean result;

    /**
     * 异常说明
     */
    private String exception;


}
