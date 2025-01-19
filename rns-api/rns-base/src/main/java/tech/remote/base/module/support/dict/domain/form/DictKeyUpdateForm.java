package tech.remote.base.module.support.dict.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 字典
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022/5/26 19:40:55
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class DictKeyUpdateForm extends DictKeyAddForm {

    @Schema(description = "keyId")
    @NotNull(message = "keyId不能为空")
    private Long dictKeyId;
}