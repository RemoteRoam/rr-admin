package tech.remote.admin.module.business.measurement.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldBigDecimal;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 仪器计量实验室任务表 实体类
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_measurement_task")
public class MeasurementTaskEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 实验室任务编号
     */
    private String taskNo;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 三方机构ID（实验室ID）
     */
    private Long thirdPartyId;

    /**
     * 仪器名称
     */
    @DataTracerFieldLabel("仪器名称")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String instrument;

    /**
     * 有效期
     */
    @DataTracerFieldLabel("有效期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate expiryDate;

    /**
     * 实验费金额
     */
    @DataTracerFieldLabel("实验费金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal experimentAmount;

    /**
     * 成本价
     */
    @DataTracerFieldLabel("成本价")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal costAmount;

    /**
     * 计量日期
     */
    @DataTracerFieldLabel("计量日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate measurementDate;

    /**
     * 完成日期
     */
    @DataTracerFieldLabel("完成日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate finishDate;

    /**
     * 状态（ProjectStatusEnum）
     */
    private Integer status;

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