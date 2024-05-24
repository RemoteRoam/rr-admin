package tech.remote.admin.module.business.measurement.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 仪器计量实验室任务表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Data
public class MeasurementTaskVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "实验室任务编号")
    private String taskNo;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "三方机构ID（实验室ID）")
    private Long thirdPartyId;

    @Schema(description = "实验室名称")
    private String thirdPartyName;

    @Schema(description = "仪器名称")
    private String instrument;

    @Schema(description = "有效期")
    private LocalDate expiryDate;

    @Schema(description = "实验费金额")
    private BigDecimal experimentAmount;

    @Schema(description = "成本价")
    private BigDecimal costAmount;

    @Schema(description = "计量日期")
    private LocalDate measurementDate;

    @Schema(description = "完成日期")
    private LocalDate finishDate;

    @Schema(description = "状态（ProjectStatusEnum）")
    private Integer status;

    @Schema(description = "操作人")
    private Long operateUserId;

    @Schema(description = "操作人姓名")
    private String operateUserName;

    @Schema(description = "操作时间")
    private LocalDateTime operateTime;

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
    private List<MeasurementNodeVO> measurementNodeList;

}