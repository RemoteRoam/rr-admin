package tech.remote.admin.module.business.measurement.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 仪器计量试验室任务表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementTaskQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

}