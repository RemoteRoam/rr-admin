package tech.remote.base.module.support.captcha.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 图形验证码 表单
 *
 * @Author 远游工作室-yy
 * @Date 2021-09-02 20:21:10
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

@Data
public class CaptchaForm {

    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captchaCode;

    @Schema(description = "验证码uuid标识")
    @NotBlank(message = "验证码uuid标识不能为空")
    private String captchaUuid;
}
