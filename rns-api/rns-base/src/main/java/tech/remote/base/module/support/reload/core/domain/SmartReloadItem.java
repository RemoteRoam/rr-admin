package tech.remote.base.module.support.reload.core.domain;

import lombok.Data;

/**
 * reload项目
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class SmartReloadItem {

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

}
