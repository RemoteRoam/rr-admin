package tech.remote.admin.module.business.systemcertification.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldBigDecimal;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 体系认证表 实体类
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_system_certification")
public class SystemCertificationEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 原始项目ID
     */
    private Long orgProjectId;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 类别
     */
    private String category;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 来源分类
     */
    @DataTracerFieldLabel("来源分类")
    private Integer sourceType;

    /**
     * 来源ID
     */
    @DataTracerFieldLabel("来源ID")
    private Long sourceId;

    /**
     * 认证机构ID
     */
    @DataTracerFieldLabel("认证机构ID")
    private Long thirdPartyId;
    
    @DataTracerFieldLabel("证书数量")
    private Integer certificateCount;

    /**
     * 合同号
     */
    @DataTracerFieldLabel("合同号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String contractNo;

    /**
     * 合同日
     */
    @DataTracerFieldLabel("合同日")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate contractDate;

    /**
     * 合同金额
     */
    @DataTracerFieldLabel("合同金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal contractAmount;

    /**
     * 客户预期日期
     */
    @DataTracerFieldLabel("客户预期日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate expectedDate;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 首款金额
     */
    @DataTracerFieldLabel("首款金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal firstPaymentAmount;

    /**
     * 首款收款日期
     */
    @DataTracerFieldLabel("首款收款日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate firstPaymentDate;

    /**
     * 体系文件传递日期
     */
    @DataTracerFieldLabel("体系文件传递日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate systemFileTransferDate;

    /**
     * 前期资料传递日期
     */
    @DataTracerFieldLabel("前期资料传递日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate preDataTransferDate;

    /**
     * 资料上报日期
     */
    @DataTracerFieldLabel("资料上报日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate dataReportDate;

    /**
     * 审核老师
     */
    @DataTracerFieldLabel("审核老师")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String auditTeacher;

    /**
     * 审核开始日期
     */
    @DataTracerFieldLabel("审核开始日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate auditDate;

    /**
     * 审核结束日期
     */
    @DataTracerFieldLabel("审核结束日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate auditDateEnd;

    /**
     * 咨询老师
     */
    @DataTracerFieldLabel("咨询老师")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String consultationTeacher;

    /**
     * 交卷日期
     */
    @DataTracerFieldLabel("交卷日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate submissionDate;

    /**
     * 评定日期
     */
    @DataTracerFieldLabel("评定日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate assessmentDate;

    /**
     * 整改日期
     */
    @DataTracerFieldLabel("整改日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate rectificationDate;

    /**
     * 证书发送日期
     */
    @DataTracerFieldLabel("证书发送日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate certificateSendDate;

    /**
     * 证书有效期截止日期
     */
    @DataTracerFieldLabel("有效期截止日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate certificateExpiryDate;

    /**
     * 尾款金额
     */
    @DataTracerFieldLabel("尾款金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal finalPaymentAmount;

    /**
     * 尾款收款日期
     */
    @DataTracerFieldLabel("尾款收款日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate finalPaymentDate;

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