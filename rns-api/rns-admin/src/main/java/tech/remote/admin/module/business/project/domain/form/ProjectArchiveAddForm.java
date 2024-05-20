package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目归档表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectArchiveAddForm {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "归档位置")
    private String archivePosition;

    @Schema(description = "归档日期")
    private LocalDate archiveDate;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}