package tech.remote.admin.module.business.project.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 项目产品表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectProductQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "实验室任务ID")
    private Long taskId;

}