package tech.remote.admin.module.business.project.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 项目认证费表 新建表单
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectCertificationFeeAddForm {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "是否付款")
    private Integer isPaid;

    @Schema(description = "付款方(客户/我方)")
    private Integer payParty;

    @Schema(description = "认证费付款日期")
    private LocalDate payDate;

    @Schema(description = "认证费备注")
    private String payRemark;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}