package tech.remote.admin.module.business.project.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 项目表 实体类
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_project")
public class ProjectEntity {

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
    @DataTracerFieldLabel("项目分类")
    private Integer category;

    /**
     * 客户ID
     */
    @DataTracerFieldLabel("客户ID")
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
     * 合同号
     */
    @DataTracerFieldLabel("合同号")
    private String contractNo;

    /**
     * 合同日
     */
    @DataTracerFieldLabel("合同日")
    private LocalDate contractDate;

    /**
     * 合同金额
     */
    @DataTracerFieldLabel("合同金额")
    private BigDecimal contractAmount;

    /**
     * 客户预期日期
     */
    @DataTracerFieldLabel("客户预期日期")
    private LocalDate expectedDate;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    private String remark;

    /**
     * 首款金额
     */
    @DataTracerFieldLabel("首款金额")
    private BigDecimal firstPaymentAmount;

    /**
     * 首款收款日期
     */
    @DataTracerFieldLabel("首款收款日期")
    private LocalDate firstPaymentDate;

    /**
     * 审核任务时间
     */
    @DataTracerFieldLabel("审核任务时间")
    private LocalDate auditTaskDate;

    /**
     * 审核老师
     */
    @DataTracerFieldLabel("审核老师")
    private String auditTeacher;

    /**
     * 咨询老师
     */
    @DataTracerFieldLabel("咨询老师")
    private String consultationTeacher;

    /**
     * 审核日期
     */
    @DataTracerFieldLabel("审核日期")
    private LocalDate auditDate;

    /**
     * 整改内容
     */
    @DataTracerFieldLabel("整改内容")
    private String rectificationContent;

    /**
     * 抽检实验完成日期 samplingTestDate
     */
    @DataTracerFieldLabel("抽检实验完成日期")
    private LocalDate samplingTestDate;

    /**
     * 整改日期
     */
    @DataTracerFieldLabel("整改日期")
    private LocalDate rectificationDate;

    /**
     * 尾款金额
     */
    @DataTracerFieldLabel("尾款金额")
    private BigDecimal finalPaymentAmount;

    /**
     * 尾款收款日期
     */
    @DataTracerFieldLabel("尾款收款日期")
    private LocalDate finalPaymentDate;

    /**
     * 开票日期
     */
    @DataTracerFieldLabel("开票日期")
    private LocalDate invoiceDate;

    /**
     * 发票金额
     */
    @DataTracerFieldLabel("发票金额")
    private BigDecimal invoiceAmount;

    /**
     * 发票号
     */
    @DataTracerFieldLabel("发票号")
    private String invoiceNumber;

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