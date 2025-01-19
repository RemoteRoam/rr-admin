package tech.remote.admin.module.business.node.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 节点表 实体类
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_node")
public class NodeEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 节点code
     */
    private String nodeCode;

    /**
     * 节点名
     */
    private String nodeName;

    /**
     * 节点级别（1项目 2任务 3产品）
     */
    private Integer nodeLevel;

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