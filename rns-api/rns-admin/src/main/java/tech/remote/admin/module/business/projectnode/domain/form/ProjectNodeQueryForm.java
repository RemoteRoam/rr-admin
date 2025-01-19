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

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 试验室任务ID
     */
    private Long taskId;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 节点级别（1项目 2任务 3产品）
     */
    private Integer nodeLevel;
}