package tech.remote.base.module.support.helpdoc.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 更新 帮助文档
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-20 23:11:42
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class HelpDocUpdateForm extends HelpDocAddForm {

    @Schema(description = "id")
    @NotNull(message = "通知id不能为空")
    private Long helpDocId;

}
