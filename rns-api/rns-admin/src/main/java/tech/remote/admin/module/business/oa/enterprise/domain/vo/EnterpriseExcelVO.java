package tech.remote.admin.module.business.oa.enterprise.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 企业信息
 *
 * @Author 远游工作室: 开云
 * @Date 2022/7/28 20:37:15
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class EnterpriseExcelVO {

    @ExcelProperty("企业名称")
    private String enterpriseName;

    @ExcelProperty("统一社会信用代码")
    private String unifiedSocialCreditCode;

    @ExcelProperty("企业类型")
    private String typeName;

    @ExcelProperty("联系人")
    private String contact;

    @ExcelProperty("联系人电话")
    private String contactPhone;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("省份名称")
    private String provinceName;

    @ExcelProperty("城市名称")
    private String cityName;

    @ExcelProperty("区县名称")
    private String districtName;

    @ExcelProperty("详细地址")
    private String address;

}
