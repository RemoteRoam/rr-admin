package tech.remote.admin.module.business.projectnode.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目流程节点表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectNodeSubListVO {


    @Schema(description = "编号")
    private Long id;

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

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "跳过原因")
    private String passReason;

    @Schema(description = "操作人")
    private Long operateUserId;

    @Schema(description = "操作人姓名")
    private String operateUserName;

    @Schema(description = "操作时间")
    private LocalDateTime operateTime;


}