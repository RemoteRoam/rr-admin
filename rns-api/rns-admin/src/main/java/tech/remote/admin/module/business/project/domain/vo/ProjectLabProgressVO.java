package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目实验室进度VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabProgressVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "实验室名称")
    private String thirdPartyName;

//    @Schema(description = "资料发送日期")
//    private LocalDate dataSendDate;

    @Schema(description = "资料接收日期")
    private LocalDate dataReceiveDate;

    @Schema(description = "实验费付款日期")
    private LocalDate labPayDate;

//    @Schema(description = "实验室下达任务日期")
//    private LocalDate assignTaskDate;

    @Schema(description = "预计完成日期")
    private LocalDate expectedCompletionDate;

    @Schema(description = "报告完成日期")
    private LocalDate reportCompletionDate;

    @Schema(description = "审核任务时间")
    private LocalDate auditTaskDate;

    @Schema(description = "产品列表")
    private List<ProjectProductProgressVO> projectProductList;

}