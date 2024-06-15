package tech.remote.admin.module.business.goods.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;

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

    @ExcelProperty("销售单号")
    @ColumnWidth(20)
    private String salesNo;

    @ExcelProperty("销售类型")
    private String salesTypeStr;

    @ExcelProperty("销售公司")
    @ColumnWidth(20)
    private String companyStr;

    @ExcelProperty("供货厂家")
    private String supplier;

    @ExcelProperty("客户")
    private String customerName;

    @ExcelProperty("商品分类")
    private String categoryName;

    @ExcelProperty("商品名称")
    private String goodsName;

    @ExcelProperty("型号规格名称")
    private String skuName;

    @ExcelProperty("重量")
    private BigDecimal weight;

    @ExcelProperty("数量")
    private Integer quantity;

    @ExcelProperty("单价")
    private BigDecimal unitPrice;

    @ExcelProperty("销售经理")
    private String managerName;

    @ExcelProperty("合同号")
    private String contractNo;

    @ExcelProperty("合同日")
    private LocalDate contractDate;

    @ExcelProperty("合同金额")
    private BigDecimal contractAmount;

    @ExcelProperty("回款日期")
    private LocalDate receivedPaymentDate;

    @ExcelProperty("回款金额")
    private BigDecimal receivedPaymentAmount;

    @ExcelProperty("发货日期")
    private LocalDate shippingDate;

    @ExcelProperty("发货金额")
    private BigDecimal shippingAmount;

    @ExcelProperty("开票日期")
    private LocalDate invoiceDate;

    @ExcelProperty("发票金额")
    private BigDecimal invoiceAmount;

    @ExcelProperty("发票号")
    private String invoiceNumber;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建人姓名")
    private String createUserName;

    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private LocalDateTime createTime;

}