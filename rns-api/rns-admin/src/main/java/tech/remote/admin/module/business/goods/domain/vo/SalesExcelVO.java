package tech.remote.admin.module.business.goods.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentLoopMerge;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import lombok.Data;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售单表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class SalesExcelVO {


    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("合同编号")
    private String contractNo;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("单位名称")
    private String customerName;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("供货厂家")
    private String supplier;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("产品名称")
    private String goodsName;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("实际重量")
    private BigDecimal weight;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("单价")
    private BigDecimal unitPrice;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("合同日期")
    private LocalDate contractDate;

    @HeadStyle(fillForegroundColor = 50)
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("合同金额")
    private BigDecimal contractAmount;

    @ExcelProperty("回款日期")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private LocalDate receivedPaymentDate;

    @ExcelProperty("回款金额")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private BigDecimal receivedPaymentAmount;

    @ExcelProperty("发货日期")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private LocalDate shippingDate;

    @ExcelProperty("发货金额")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private BigDecimal shippingAmount;

    @ExcelProperty("发票日期")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private LocalDate invoiceDate;

    @ExcelProperty("发票金额")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private BigDecimal invoiceAmount;

    @ExcelProperty("发票号")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private String invoiceNumber;

    @HeadStyle(fillForegroundColor = 50)
    @ExcelProperty("销售经理")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private String managerName;


    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ExcelProperty("销售单号")
    @ColumnWidth(20)
    private String salesNo;

    @ExcelProperty("销售类型")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private String salesTypeStr;

    @ExcelProperty("销售公司")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    @ColumnWidth(20)
    private String companyStr;

    @ExcelProperty("商品分类")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private String categoryName;

    @ExcelProperty("备注")
    @ContentStyle(borderRight = BorderStyleEnum.THIN,
            borderBottom = BorderStyleEnum.THIN,
            borderLeft = BorderStyleEnum.THIN,
            borderTop = BorderStyleEnum.THIN)
    private String remark;

}