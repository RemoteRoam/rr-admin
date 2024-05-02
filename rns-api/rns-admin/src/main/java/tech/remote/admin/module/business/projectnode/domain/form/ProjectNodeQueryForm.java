package tech.remote.admin.module.business.projectnode.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 项目流程节点表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectNodeQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

}