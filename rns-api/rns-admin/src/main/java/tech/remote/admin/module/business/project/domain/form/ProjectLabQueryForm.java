package tech.remote.admin.module.business.project.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 项目实验室任务表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "三方机构ID（实验室ID）")
    private Long thirdPartyId;

}