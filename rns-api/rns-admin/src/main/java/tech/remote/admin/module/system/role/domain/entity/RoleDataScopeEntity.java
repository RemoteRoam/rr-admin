package tech.remote.admin.module.system.role.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import tech.remote.admin.module.system.datascope.constant.DataScopeTypeEnum;
import tech.remote.admin.module.system.datascope.constant.DataScopeViewTypeEnum;

import java.time.LocalDateTime;

/**
 * 数据范围与角色关系
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-03-07 18:54:42
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
@TableName("t_role_data_scope")
public class RoleDataScopeEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 数据范围id
     * {@link DataScopeTypeEnum}
     */
    private Integer dataScopeType;
    /**
     * 数据范围类型
     * {@link DataScopeViewTypeEnum}
     */
    private Integer viewType;
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
