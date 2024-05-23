package tech.remote.admin.module.business.measurement.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仪器计量表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class MeasurementExcelVO {

    @ExcelProperty("项目编号")
    private String projectNo;

    @ExcelProperty("项目类型")
    private Integer projectType;

    @ExcelProperty("客户")
    private String customerName;

    @ExcelProperty("负责人")
    private String managerName;

    @ExcelProperty("实验室名称")
    private String thirdPartyName;

    @ExcelProperty("仪器名称")
    @ColumnWidth(30)
    private String instrument;

    @ExcelProperty("有效期")
    private LocalDate expiryDate;

    @ExcelProperty("实验费金额")
    private BigDecimal experimentAmount;

    @ExcelProperty("成本价")
    private BigDecimal costAmount;

    @ExcelProperty("计量日期")
    private LocalDate measurementDate;

    @ExcelProperty("完成日期")
    private LocalDate finishDate;

    @ExcelProperty("收款金额")
    private BigDecimal paymentAmount;

    @ExcelProperty("收款日期")
    private LocalDate paymentDate;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("开票日期")
    private LocalDate invoiceDate;

    @ExcelProperty("发票金额")
    private BigDecimal invoiceAmount;

    @ExcelProperty("发票号")
    private String invoiceNumber;

    @ExcelProperty("邮寄日期")
    private LocalDate mailingDate;

    @ExcelProperty("快递单号")
    private String trackingNumber;

    @ExcelProperty("创建人")
    private String createUserName;

    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private LocalDateTime createTime;

    @ExcelProperty("更新人")
    private String updateUserName;

    @ExcelProperty("更新时间")
    @ColumnWidth(20)
    private LocalDateTime updateTime;

}