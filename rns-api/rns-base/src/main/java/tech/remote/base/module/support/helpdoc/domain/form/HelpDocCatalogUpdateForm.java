package tech.remote.base.module.support.helpdoc.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 帮助文档 目录
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-20 23:11:42
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class HelpDocCatalogUpdateForm extends HelpDocCatalogAddForm {

    @Schema(description = "id")
    @NotNull(message = "id")
    private Long helpDocCatalogId;
}
