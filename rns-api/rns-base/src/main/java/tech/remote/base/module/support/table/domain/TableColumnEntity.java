package tech.remote.base.module.support.table.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 自定义表格列
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-12 22:52:21
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
@TableName("t_table_column")
public class TableColumnEntity {

    @TableId(type = IdType.AUTO)
    private Long tableColumnId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 表id
     */
    private Integer tableId;

    /**
     * 表列
     */
    private String columns;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
