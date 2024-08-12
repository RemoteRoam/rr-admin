package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;

/**
 * 项目表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "原始项目ID")
    private Long orgProjectId;

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "项目分类")
    private Integer category;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "客户")
    private String customerName;

    @Schema(description = "来源分类")
    private Integer sourceType;

    @Schema(description = "来源ID")
    private Long sourceId;

    @Schema(description = "来源")
    private String sourceName;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同日")
    private LocalDate contractDate;

    @Schema(description = "合同金额")
    private BigDecimal contractAmount;

    @Schema(description = "客户预期日期")
    private LocalDate expectedDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "首款金额")
    private BigDecimal firstPaymentAmount;

    @Schema(description = "首款收款日期")
    private LocalDate firstPaymentDate;

    @Schema(description = "审核任务时间")
    private LocalDate auditTaskDate;

    @Schema(description = "审核老师")
    private String auditTeacher;

    @Schema(description = "咨询老师")
    private String consultationTeacher;

    @Schema(description = "审核日期")
    private LocalDate auditDate;

    @Schema(description = "整改内容")
    private String rectificationContent;

    @Schema(description = "整改日期")
    private LocalDate rectificationDate;

    @Schema(description = "抽检实验完成日期")
    private LocalDate samplingTestDate;

    @Schema(description = "尾款金额")
    private BigDecimal finalPaymentAmount;

    @Schema(description = "尾款收款日期")
    private LocalDate finalPaymentDate;

    @Schema(description = "开票日期")
    private LocalDate invoiceDate;

    @Schema(description = "发票金额")
    private BigDecimal invoiceAmount;

    @Schema(description = "发票号")
    private String invoiceNumber;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "产品监督证书到期日")
    private LocalDate certificateExpiryDate;

    @Schema(description = "上年度审核日期")
    private LocalDate lastYearAuditDate;

    @Schema(description = "实验室任务列表")
    private List<ProjectLabVO> projectLabList;

    @Schema(description = "操作节点列表")
    private List<ProjectNodeVO> projectNodeList;

}