package tech.remote.admin.module.business.systemcertification.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

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
     * 项目分类
     */
    private String category;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 来源分类
     */
    private Integer sourceType;

    /**
     * 来源ID
     */
    private Long sourceId;

    /**
     * 认证机构ID
     */
    private Long thirdPartyId;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 合同日
     */
    private LocalDate contractDate;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount;

    /**
     * 客户预期日期
     */
    private LocalDate expectedDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 首款金额
     */
    private BigDecimal firstPaymentAmount;

    /**
     * 首款收款日期
     */
    private LocalDate firstPaymentDate;

    /**
     * 资料上报日期
     */
    private LocalDate dataReportDate;

    /**
     * 审核老师
     */
    private String auditTeacher;

    /**
     * 审核日期
     */
    private LocalDate auditDate;

    /**
     * 咨询老师
     */
    private String consultationTeacher;

    /**
     * 交卷日期
     */
    private LocalDate submissionDate;

    /**
     * 评定日期
     */
    private LocalDate assessmentDate;

    /**
     * 整改日期
     */
    private LocalDate rectificationDate;

    /**
     * 证书发送日期
     */
    private LocalDate certificateSendDate;

    /**
     * 证书有效期截止日期
     */
    private LocalDate certificateExpiryDate;

    /**
     * 尾款金额
     */
    private BigDecimal finalPaymentAmount;

    /**
     * 尾款收款日期
     */
    private LocalDate finalPaymentDate;

    /**
     * 开票日期
     */
    private LocalDate invoiceDate;

    /**
     * 发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 发票号
     */
    private String invoiceNumber;

    /**
     * 邮寄日期
     */
    private LocalDate mailingDate;

    /**
     * 快递单号
     */
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