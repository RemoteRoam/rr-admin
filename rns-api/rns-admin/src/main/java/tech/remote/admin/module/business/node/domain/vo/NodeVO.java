package tech.remote.admin.module.business.node.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 节点表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Data
public class NodeVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "节点code")
    private String nodeCode;

    @Schema(description = "节点名")
    private String nodeName;

    @Schema(description = "节点级别（1项目 2任务 3产品）")
    private Integer nodeLevel;

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