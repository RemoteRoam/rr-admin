package tech.remote.admin.module.business.systemcertification.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.base.common.json.serializer.DictValueVoSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 体系认证表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class SystemCertificationExcelVO {

    @ExcelProperty("项目编号")
    private String projectNo;

    @ExcelProperty("项目类型")
    private String projectTypeName;

    @ExcelProperty("类别")
    @JsonSerialize(using = DictValueVoSerializer.class)
    private String category;

    @ExcelProperty("客户")
    private String customerName;

    @ExcelProperty("来源")
    private String sourceName;

    @ExcelProperty("认证机构")
    private String thirdPartyName;

    @ExcelProperty("证书数量")
    private Integer certificateCount;

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

    @ExcelProperty("体系文件传递日期")
    private LocalDate systemFileTransferDate;

    @ExcelProperty("前期资料传递日期")
    private LocalDate preDataTransferDate;

    @ExcelProperty("资料上报日期")
    private LocalDate dataReportDate;

    @ExcelProperty("审核老师")
    private String auditTeacher;

    @ExcelProperty("审核开始日期")
    private LocalDate auditDate;

    @ExcelProperty("审核结束日期")
    private LocalDate auditDateEnd;

    @ExcelProperty("咨询老师")
    private String consultationTeacher;

    @ExcelProperty("交卷日期")
    private LocalDate submissionDate;

    @ExcelProperty("评定日期")
    private LocalDate assessmentDate;

    @ExcelProperty("整改日期")
    private LocalDate rectificationDate;

    @ExcelProperty("证书发送日期")
    private LocalDate certificateSendDate;

    @ExcelProperty("证书有效期截止日期")
    private LocalDate certificateExpiryDate;

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