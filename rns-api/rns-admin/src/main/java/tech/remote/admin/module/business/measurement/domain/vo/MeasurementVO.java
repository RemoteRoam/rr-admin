package tech.remote.admin.module.business.measurement.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;

/**
 * 仪器计量表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目编号")
    private String projectNo;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "负责人ID")
    private Long managerId;

    @Schema(description = "收款金额")
    private BigDecimal paymentAmount;

    @Schema(description = "收款日期")
    private LocalDate paymentDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "开票日期")
    private LocalDate invoiceDate;

    @Schema(description = "发票金额")
    private BigDecimal invoiceAmount;

    @Schema(description = "发票号")
    private String invoiceNumber;

    @Schema(description = "邮寄日期")
    private LocalDate mailingDate;

    @Schema(description = "快递单号")
    private String trackingNumber;

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

    @Schema(description = "操作节点列表")
    private List<ProjectNodeVO> projectNodeList;

}