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
 * 项目实验室任务表 实体类
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_project_lab")
public class ProjectLabEntity {

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
     * 实验室任务编号
     */
    private String taskNo;

    /**
     * 三方机构ID（实验室ID）
     */
    private Long thirdPartyId;

    /**
     * 实验室合同号
     */
    @DataTracerFieldLabel("实验室合同号")
    private String labContractNo;

    /**
     * 实验室合同日期
     */
    @DataTracerFieldLabel("实验室合同日期")
    private LocalDate labContractDate;

    /**
     * 实验费金额
     */
    @DataTracerFieldLabel("实验费金额")
    private BigDecimal labContractAmount;

    /**
     * 客户要求完成日期
     */
    @DataTracerFieldLabel("客户要求完成日期")
    private LocalDate labExpectedDate;

    /**
     * 实验合同备注
     */
    @DataTracerFieldLabel("实验合同备注")
    private String labContractRemark;

    /**
     * 资料发送日期
     */
    @DataTracerFieldLabel("资料发送日期")
    private LocalDate dataSendDate;

    /**
     * 资料接收日期
     */
    @DataTracerFieldLabel("资料接收日期")
    private LocalDate dataReceiveDate;

    /**
     * 是否付款
     */
    @DataTracerFieldLabel("是否付款")
    private Integer isPaid;

    /**
     * 付款方(客户/我方)
     */
    @DataTracerFieldLabel("付款方")
    private Integer payParty;

    /**
     * 实验费付款日期
     */
    @DataTracerFieldLabel("实验费付款日期")
    private LocalDate labPayDate;

    /**
     * 实验费备注
     */
    @DataTracerFieldLabel("实验费备注")
    private String labPayRemark;

    /**
     * 实验室下达任务日期
     */
    @DataTracerFieldLabel("实验室下达任务日期")
    private LocalDate assignTaskDate;

    /**
     * 预计完成日期
     */
    @DataTracerFieldLabel("预计完成日期")
    private LocalDate expectedCompletionDate;

    /**
     * 报告完成日期
     */
    @DataTracerFieldLabel("报告完成日期")
    private LocalDate reportCompletionDate;

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