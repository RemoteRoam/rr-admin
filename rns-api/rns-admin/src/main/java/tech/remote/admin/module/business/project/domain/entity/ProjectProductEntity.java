package tech.remote.admin.module.business.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

/**
 * 项目产品表 实体类
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_project_product")
public class ProjectProductEntity {

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
     * 实验室任务ID
     */
    private Long taskId;

    /**
     * 产品名称
     */
    @DataTracerFieldLabel("产品名称")
    private String productName;

    /**
     * 产品型号
     */
    @DataTracerFieldLabel("产品型号")
    private String productModel;

    /**
     * 实验室上报日期
     */
    @DataTracerFieldLabel("实验室上报日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate labReportDate;

    /**
     * 自我声明日期
     */
    @DataTracerFieldLabel("自我声明日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate selfDeclarationDate;

    /**
     * 证书编号
     */
    @DataTracerFieldLabel("证书编号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String certificateNo;

    /**
     * 证书发送日期
     */
    @DataTracerFieldLabel("证书发送日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate certificateSendDate;

    /**
     * 证书有效期截止日期
     */
    @DataTracerFieldLabel("证书有效期截止日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate certificateExpiryDate;

    /**
     * 项目认证费表ID
     */
    private Long certificationFeeId;

    /**
     * 项目归档表ID
     */
    private Long archiveId;

    /**
     * 项目邮寄表ID
     */
    private Long mailId;

    /**
     * 状态
     */
    private Integer status;

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