package tech.remote.admin.module.business.measurement.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 仪器计量流程节点表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementNodeAddForm {

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "试验室任务ID")
    private Long taskId;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "节点排序")
    private Integer nodeSort;

    @Schema(description = "状态（NodeStatusEnum）")
    private Integer status;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}