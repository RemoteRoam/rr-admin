package tech.remote.base.module.support.reload.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * reload结果
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class ReloadResultVO {

    @Schema(description = "加载项标签")
    private String tag;

    @Schema(description = "参数")
    private String args;

    @Schema(description = "运行结果")
    private Boolean result;

    @Schema(description = "异常")
    private String exception;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
