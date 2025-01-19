package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;

import java.time.LocalDate;

@Data
public class ProjectLabListQueryForm extends PageParam {
    @Schema(description = "三方机构ID（试验室ID）")
    private Long thirdPartyId;
    @Schema(description = "项目编号")
    private String projectNo;
    @Schema(description = "项目类型")
    private Integer projectType;
    @Schema(description = "项目分类")
    private Integer category;
    @Schema(description = "项目名称")
    private String projectName;
    @Schema(description = "客户ID")
    private Long customerId;
    @Schema(description = "来源分类")
    private Integer sourceType;
    @Schema(description = "来源ID")
    private Long sourceId;
    private Long operateUserId;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "试验室合同编号")
    private String labContractNo;

    @Schema(description = "是否付款")
    private Integer isPaid;
    @Schema(description = "付款方")
    private Integer payParty;

    private LocalDate labPayDateBegin;
    private LocalDate labPayDateEnd;
    @Schema(description = "产品名称")
    private String productName;
    @Schema(description = "产品型号")
    private String productModel;

    @Schema(description = "待办类型：1 办公室待办，2 试验室待办")
    private Integer toDoType;

    @Schema(description = "预警类型：2 试验室任务预警")
    private Integer alarmType;

    @Schema(description = "节点ID：8预计完成 9试验检测 10试验室上报")
    private Integer nodeId;
}
