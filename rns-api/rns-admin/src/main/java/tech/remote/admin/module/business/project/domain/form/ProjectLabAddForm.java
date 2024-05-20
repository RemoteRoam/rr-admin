package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 项目实验室任务表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectLabAddForm {


    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "三方机构ID（实验室ID）")
    private Long thirdPartyId;

    @Schema(description = "产品列表")
    private List<ProjectProductAddForm> products;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}