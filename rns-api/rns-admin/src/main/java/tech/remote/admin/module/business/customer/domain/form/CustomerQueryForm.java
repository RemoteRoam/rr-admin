package tech.remote.admin.module.business.customer.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 客户表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Data
public class CustomerQueryForm extends PageParam{

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "客户类型(1认证客户2销售客户)")
    private Integer type;

    @Schema(description = "客户级别")
    private String customerLevel;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系人电话")
    private String contactPhone;

    @Schema(description = "省份")
    private Integer province;

    @Schema(description = "市")
    private Integer city;

    @Schema(description = "区县")
    private Integer district;

    @Schema(description = "工厂省份")
    private String factoryProvince;

    @Schema(description = "工厂市")
    private String factoryCity;

    @Schema(description = "工厂区县")
    private String factoryDistrict;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

}