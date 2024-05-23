package tech.remote.admin.module.business.project.domain.vo;

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
 * 项目表 列表VO
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class ProjectExcelVO {

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

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("首款金额")
    private BigDecimal firstPaymentAmount;

    @ExcelProperty("首款收款日期")
    private LocalDate firstPaymentDate;

    @ExcelProperty("实验室名称")
    private String thirdPartyName;

    @ExcelProperty("实验室合同号")
    private String labContractNo;

    @ExcelProperty("实验室合同日期")
    private LocalDate labContractDate;

    @ExcelProperty("实验费金额")
    private BigDecimal labContractAmount;

    @ExcelProperty("客户要求完成日期")
    private LocalDate labExpectedDate;

    @ExcelProperty("实验合同备注")
    private String labContractRemark;

    @ExcelProperty("资料发送日期")
    private LocalDate dataSendDate;

    @ExcelProperty("资料接收日期")
    private LocalDate dataReceiveDate;

    @ExcelProperty("实验费是否付款")
    private String isPaid;

    @ExcelProperty("实验费付款方")
    private String payParty;

    @ExcelProperty("实验费付款日期")
    private LocalDate labPayDate;

    @ExcelProperty("实验费备注")
    private String labPayRemark;

    @ExcelProperty("实验室下达任务日期")
    private LocalDate assignTaskDate;

    @ExcelProperty("预计完成日期")
    private LocalDate expectedCompletionDate;

    @ExcelProperty("报告完成日期")
    private LocalDate reportCompletionDate;


    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("产品型号")
    private String productModel;

    @ExcelProperty("实验室上报日期")
    private LocalDate labReportDate;

    @ExcelProperty("自我声明日期")
    private LocalDate selfDeclarationDate;

    @ExcelProperty("证书编号")
    private String certificateNo;

    @ExcelProperty("证书发送日期")
    private LocalDate certificateSendDate;

    @ExcelProperty("证书有效期截止日期")
    private LocalDate certificateExpiryDate;

    @ExcelProperty("认证费是否付款")
    private String feeIsPaid;

    @ExcelProperty("认证费付款方")
    private String feePayParty;

    @ExcelProperty("认证费付款日期")
    private LocalDate payDate;

    @ExcelProperty("认证费备注")
    private String payRemark;

    @ExcelProperty("归档位置")
    private String archivePosition;

    @ExcelProperty("归档日期")
    private LocalDate archiveDate;

    @ExcelProperty("邮寄日期")
    private LocalDate mailingDate;

    @ExcelProperty("快递单号")
    private String trackingNumber;

    @ExcelProperty("审核任务时间")
    private LocalDate auditTaskDate;

    @ExcelProperty("审核老师")
    private String auditTeacher;

    @ExcelProperty("咨询老师")
    private String consultationTeacher;

    @ExcelProperty("审核日期")
    private LocalDate auditDate;

    @ExcelProperty("整改内容")
    private String rectificationContent;

    @ExcelProperty("整改日期")
    private LocalDate rectificationDate;

    @ExcelProperty("尾款金额")
    private BigDecimal finalPaymentAmount;

    @ExcelProperty("尾款收款日期")
    private LocalDate finalPaymentDate;

    @ExcelProperty("开票日期")
    private LocalDate invoiceDate;

    @ExcelProperty("发票金额")
    private BigDecimal invoiceAmount;

    @ExcelProperty("发票号")
    private String invoiceNumber;

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