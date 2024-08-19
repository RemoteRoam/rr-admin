package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目试验室任务表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabListVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "试验室任务编号")
    private String taskNo;

    @Schema(description = "三方机构ID（试验室ID）")
    private Long thirdPartyId;

    @Schema(description = "试验室名称")
    private String thirdPartyName;

    @Schema(description = "试验室合同号")
    private String labContractNo;

    @Schema(description = "试验室合同日期")
    private LocalDate labContractDate;

    @Schema(description = "试验费金额")
    private BigDecimal labContractAmount;

    @Schema(description = "客户要求完成日期")
    private LocalDate labExpectedDate;

    @Schema(description = "试验合同备注")
    private String labContractRemark;

    @Schema(description = "资料发送日期")
    private LocalDate dataSendDate;

    @Schema(description = "资料接收日期")
    private LocalDate dataReceiveDate;

    @Schema(description = "是否付款")
    private Integer isPaid;

    @Schema(description = "付款方(客户/我方)")
    private Integer payParty;

    @Schema(description = "试验费付款日期")
    private LocalDate labPayDate;

    @Schema(description = "试验费备注")
    private String labPayRemark;

    @Schema(description = "试验室下达任务日期")
    private LocalDate assignTaskDate;

    @Schema(description = "预计完成日期")
    private LocalDate expectedCompletionDate;

    @Schema(description = "报告完成日期")
    private LocalDate reportCompletionDate;

    @Schema(description = "自我声明日期")
    private LocalDate selfDeclarationDate;

    @Schema(description = "证书发送日期")
    private LocalDate certificateSendDate;

    @Schema(description = "邮寄日期")
    private LocalDate mailingDate;

    @Schema(description = "进度查询CODE")
    private String progressCode;

    @Schema(description = "状态")
    private Integer status;

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

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目状态")
    private Integer projectStatus;

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

    @Schema(description = "试验室预警天数")
    private Integer alarmDays;

    @Schema(description = "产品列表")
    private List<ProjectProductVO> projectProductList;

    @Schema(description = "操作节点列表")
    private List<ProjectNodeVO> projectNodeList;
}