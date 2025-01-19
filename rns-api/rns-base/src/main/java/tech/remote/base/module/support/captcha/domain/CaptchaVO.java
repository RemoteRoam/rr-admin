package tech.remote.base.module.support.captcha.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 图形验证码 VO
 *
 * @Author 远游工作室-yy
 * @Date 2021/8/31 20:52
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class CaptchaVO {

    @Schema(description = "验证码唯一标识")
    private String captchaUuid;

    @Schema(description = "验证码图片内容-生产环境无效")
    private String captchaText;

    @Schema(description = "验证码Base64图片")
    private String captchaBase64Image;

    @Schema(description = "过期时间（秒）")
    private Long expireSeconds;
}
