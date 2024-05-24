package tech.remote.admin.module.business.project.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目产品表 进度VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Data
public class ProjectProductProgressVO {

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品型号")
    private String productModel;

//    @Schema(description = "实验室上报日期")
//    private LocalDate labReportDate;

    @Schema(description = "自我声明日期")
    private LocalDate selfDeclarationDate;

    @Schema(description = "证书发送日期")
    private LocalDate certificateSendDate;

    @Schema(description = "认证费付款日期")
    private LocalDate payDate;

}