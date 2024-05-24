package tech.remote.admin.module.business.measurement.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 仪器计量流程节点表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementNodeQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "实验室任务ID")
    private Long taskId;

}