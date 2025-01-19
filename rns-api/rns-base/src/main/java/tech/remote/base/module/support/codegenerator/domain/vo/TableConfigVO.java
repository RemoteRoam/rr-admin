
package tech.remote.base.module.support.codegenerator.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.module.support.codegenerator.domain.model.*;
import tech.remote.base.module.support.codegenerator.domain.model.*;

import java.util.List;

/**
 * 表的配置信息
 *
 * @Author 远游工作室-yy
 * @Date 2022/9/21 21:07:58
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

@Data
public class TableConfigVO {

    @Schema(description = "基础命名信息")
    private CodeBasic basic;

    @Schema(description = "字段列")
    private List<CodeField> fields;

    @Schema(description = "增加、修改 信息")
    private CodeInsertAndUpdate insertAndUpdate;

    @Schema(description = "删除 信息")
    private CodeDelete deleteInfo;

    @Schema(description = "查询字段")
    private List<CodeQueryField> queryFields;

    @Schema(description = "列表字段")
    private List<CodeTableField> tableFields;
}
