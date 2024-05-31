package tech.remote.admin.module.business.project.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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

    @ExcelProperty("项目编号")
    private String projectNo;

    @ExcelProperty("项目类型")
    private String projectTypeName;

    @ExcelProperty("项目分类")
    private String categoryName;

    @ExcelProperty("客户")
    private String customerName;

    @ExcelProperty("来源")
    private String sourceName;

    @ExcelProperty("合同号")
    private String contractNo;

    @ExcelProperty("合同日")
    private LocalDate contractDate;

    @ExcelProperty("合同金额")
    private BigDecimal contractAmount;

    @ExcelProperty("客户预期日期")
    private LocalDate expectedDate;

    @ExcelProperty("实验室任务编号")
    @ColumnWidth(18)
    private String taskNo;

    @ExcelProperty("实验室名称")
    private String thirdPartyName;

    @ExcelProperty("实验室合同号")
    private String labContractNo;

    @ExcelProperty("实验室合同日期")
    @ColumnWidth(18)
    private LocalDate labContractDate;

    @ExcelProperty("实验费金额")
    private BigDecimal labContractAmount;

    @ExcelProperty("客户要求完成日期")
    @ColumnWidth(19)
    private LocalDate labExpectedDate;

    @ExcelProperty("实验合同备注")
    private String labContractRemark;

    @ExcelProperty("资料发送日期")
    private LocalDate dataSendDate;

    @ExcelProperty("资料接收日期")
    private LocalDate dataReceiveDate;

    @ExcelProperty("是否付款")
    private String isPaid;

    @ExcelProperty("实验费付款方")
    private String payParty;

    @ExcelProperty("实验费付款日期")
    @ColumnWidth(18)
    private LocalDate labPayDate;

    @ExcelProperty("实验费备注")
    private String labPayRemark;

    @ExcelProperty("下达任务日期")
    private LocalDate assignTaskDate;

    @ExcelProperty("预计完成日期")
    private LocalDate expectedCompletionDate;

    @ExcelProperty("报告完成日期")
    private LocalDate reportCompletionDate;

}