package tech.remote.base.module.support.codegenerator.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;


/**
 * 查询表数据
 *
 * @Author 远游工作室-yy
 * @Date 2022-06-30 22:15:38
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class TableQueryForm extends PageParam {

    @Schema(description = "表名关键字")
    private String tableNameKeywords;

}
