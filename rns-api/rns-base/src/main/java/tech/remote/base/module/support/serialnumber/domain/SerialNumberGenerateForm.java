package tech.remote.base.module.support.serialnumber.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 单据序列号 生成表单
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class SerialNumberGenerateForm {

    @Schema(description = "单号id")
    @NotNull(message = "单号id不能为空")
    private Integer serialNumberId;

    @Schema(description = "生成的数量")
    @NotNull(message = "生成的数量")
    private Integer count;

}
