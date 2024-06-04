package tech.remote.admin.module.business.project.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 项目实验表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class ProjectLabExcelVO {

    // 项目单位
    @ExcelProperty("项目编号")
    private String projectNo;

    // 项目单位
    @ExcelProperty("项目类型")
    private String projectTypeName;

    // 项目单位
    @ExcelProperty("项目分类")
    private String categoryName;

    // 项目单位
    @ExcelProperty("客户名称")
    private String customerName;

    // 项目单位
    @ExcelProperty("客户来源")
    private String sourceName;

    // 实验室单位
    @ExcelProperty("实验室合同号")
    private String labContractNo;

    // 实验室单位
    @ExcelProperty("实验室合同日期")
    @ColumnWidth(18)
    private LocalDate labContractDate;

    // 实验室单位
    @ExcelProperty("实验室")
    private String thirdPartyName;

    // 实验室单位
    @ExcelProperty("付款方")
    private String payParty;

    // 实验室单位
    @ExcelProperty("实验费付款日期")
    @ColumnWidth(18)
    private LocalDate labPayDate;

    // 实验室单位
    @ExcelProperty("客户要求完成日期")
    @ColumnWidth(19)
    private LocalDate labExpectedDate;

    // 实验室单位
    @ExcelProperty("预计完成日期")
    private LocalDate expectedCompletionDate;

    // 产品单位
    @ExcelProperty("实验室上报日期")
    @ColumnWidth(18)
    private LocalDate labReportDate;

    // 实验室单位
    @ExcelProperty("报告完成日期")
    private LocalDate reportCompletionDate;

    // 产品单位
    @ExcelProperty("自我声明日期")
    private LocalDate selfDeclarationDate;

    // 项目单位
    @ExcelProperty("审核日期")
    private LocalDate auditDate;

    // 产品单位
    @ExcelProperty("证书发送日期")
    private LocalDate certificateSendDate;

    // 产品单位
    @ExcelProperty("邮寄日期")
    private LocalDate mailingDate;

    // 实验室单位
    @ExcelProperty("状态")
    private String statusName;

}