package tech.remote.admin.module.business.projectnode.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目流程节点表 实体类
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_project_node")
public class ProjectNodeEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 实验室任务ID
     */
    private Long taskId;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 节点ID
     */
    private Long nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点级别（1项目 2任务 3产品）
     */
    private Integer nodeLevel;

    /**
     * 节点排序
     */
    private Integer nodeSort;

    /**
     * 状态（NodeStatusEnum）
     */
    private Integer status;

    /**
     * 跳过原因
     */
    private String passReason;

    /**
     * 操作人
     */
    private Long operateUserId;

    /**
     * 操作人姓名
     */
    private String operateUserName;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateUserId;

    /**
     * 更新人姓名
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}