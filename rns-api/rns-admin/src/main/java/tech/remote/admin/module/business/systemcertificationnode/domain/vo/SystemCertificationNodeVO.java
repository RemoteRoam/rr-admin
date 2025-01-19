package tech.remote.admin.module.business.systemcertificationnode.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 体系认证项目流程节点表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SystemCertificationNodeVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "节点排序")
    private Integer nodeSort;

    @Schema(description = "状态（NodeStatusEnum）")
    private Integer status;

    @Schema(description = "跳过原因")
    private String passReason;

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

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}