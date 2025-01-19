package tech.remote.base.module.support.reload.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * reload结果 <br>
 * t_reload_result 数据表 实体类
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
@TableName("t_reload_result")
public class ReloadResultEntity {

    /**
     * 加载项标签
     */
    @TableId(type= IdType.NONE)
    private String tag;

    /**
     * 运行标识
     */
    private String identification;

    /**
     * 参数
     */
    private String args;

    /**
     * 运行结果
     */
    private Boolean result;

    /**
     * 异常
     */
    private String exception;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
