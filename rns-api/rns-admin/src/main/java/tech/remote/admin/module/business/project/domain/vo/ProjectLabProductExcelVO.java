package tech.remote.admin.module.business.project.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 项目表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(16)
public class ProjectLabProductExcelVO {

    @ExcelProperty("项目编号")
    private String projectNo;

    @ExcelProperty("项目类型")
    private String projectTypeName;

    @ExcelProperty("项目分类")
    private String categoryName;

    @ExcelProperty("客户名称")
    @ColumnWidth(19)
    private String customerName;

    @ExcelProperty("来源名称")
    private String sourceName;

    @ExcelProperty("试验室名称")
    private String thirdPartyName;

    @ExcelProperty("试验室合同号")
    private String labContractNo;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("试验室合同日期")
    @ColumnWidth(19)
    private LocalDate labContractDate;

    @ExcelProperty("试验费付款方")
    private String payParty;

    @ExcelProperty("试验费金额")
    private BigDecimal labContractAmount;

}