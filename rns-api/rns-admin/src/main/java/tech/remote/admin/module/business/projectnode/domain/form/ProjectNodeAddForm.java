package tech.remote.admin.module.business.projectnode.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目流程节点表 新建表单
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectNodeAddForm {

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "试验室任务ID")
    private Long taskId;

    @Schema(description = "产品ID")
    private Long productId;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "状态（0未开始 1完成 2跳过）")
    private Integer status;

    @Schema(description = "操作人")
    private Long operateUserId;

    @Schema(description = "操作人姓名")
    private String operateUserName;

    @Schema(description = "操作时间")
    private LocalDateTime operateTime;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

}