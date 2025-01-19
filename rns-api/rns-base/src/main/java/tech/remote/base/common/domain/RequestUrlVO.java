package tech.remote.base.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 请求url返回对象
 *
 * @Author 远游工作室: 李善逸
 * @Date 2021/9/1 20:15
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RequestUrlVO {

    @Schema(description = "注释说明")
    private String comment;

    @Schema(description = "controller.method")
    private String name;

    @Schema(description = "url")
    private String url;
}
