package tech.remote.base.module.support.helpdoc.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 帮助文档的 目录
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-20 23:11:42
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class HelpDocCatalogVO {

    @Schema(description = "帮助文档目录id")
    private Long helpDocCatalogId;

    @Schema(description = "帮助文档目录-名称")
    private String name;

    @Schema(description = "帮助文档目录-父级id")
    private Long parentId;

    @Schema(description = "帮助文档目录-排序")
    private Integer sort;

}
