package tech.remote.admin.module.business.typenode.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 项目类型节点关系表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Data
public class TypeNodeQueryForm extends PageParam{

    @Schema(description = "项目类型")
    private String projectType;

}