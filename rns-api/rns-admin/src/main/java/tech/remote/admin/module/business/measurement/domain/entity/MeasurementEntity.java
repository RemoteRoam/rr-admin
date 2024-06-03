package tech.remote.admin.module.business.measurement.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldBigDecimal;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 仪器计量表 实体类
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_measurement")
public class MeasurementEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 负责人ID
     */
    @DataTracerFieldLabel("负责人")
    private Long managerId;

    /**
     * 收款金额
     */
    @DataTracerFieldLabel("收款金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal paymentAmount;

    /**
     * 收款日期
     */
    @DataTracerFieldLabel("收款日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate paymentDate;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 开票日期
     */
    @DataTracerFieldLabel("开票日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate invoiceDate;

    /**
     * 发票金额
     */
    @DataTracerFieldLabel("发票金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal invoiceAmount;

    /**
     * 发票号
     */
    @DataTracerFieldLabel("发票号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String invoiceNumber;

    /**
     * 邮寄日期
     */
    @DataTracerFieldLabel("邮寄日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate mailingDate;

    /**
     * 快递单号
     */
    @DataTracerFieldLabel("快递单号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String trackingNumber;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 删除状态
     */
    private Integer deletedFlag;

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