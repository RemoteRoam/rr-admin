package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目产品表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectProductAddForm {

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "实验室任务ID")
    private Long taskId;

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品型号")
    private String productModel;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}