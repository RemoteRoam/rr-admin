package tech.remote.admin.module.business.customer.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tech.remote.base.common.json.deserializer.DictValueVoDeserializer;

import java.time.LocalDateTime;

/**
 * 客户表 新建表单
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Data
public class CustomerAddForm {

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户名称 不能为空")
    private String customerName;

    @Schema(description = "客户类型(1认证客户2销售客户)")
    @NotNull(message = "客户类型 不能为空")
    private Integer type;


    @Schema(description = "客户级别")
    @JsonDeserialize(using = DictValueVoDeserializer.class)
    private String customerLevel;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系人电话")
    private String contactPhone;

    /**
     * 联系人2
     */
    private String contactSecond;

    /**
     * 联系人2电话
     */
    private String contactSecondPhone;

    /**
     * 联系人3
     */
    private String contactThird;

    /**
     * 联系人3电话
     */
    private String contactThirdPhone;

    @Schema(description = "省份")
    private Integer province;

    @Schema(description = "省份名称")
    private String provinceName;

    @Schema(description = "市")
    private Integer city;

    @Schema(description = "城市名称")
    private String cityName;

    @Schema(description = "区县")
    private Integer district;

    @Schema(description = "区县名称")
    private String districtName;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "工厂省份")
    private String factoryProvince;

    @Schema(description = "工厂省份名称")
    private String factoryProvinceName;

    @Schema(description = "工厂市")
    private String factoryCity;

    @Schema(description = "工厂城市名称")
    private String factoryCityName;

    @Schema(description = "工厂区县")
    private String factoryDistrict;

    @Schema(description = "工厂区县名称")
    private String factoryDistrictName;

    @Schema(description = "工厂详细地址")
    private String factoryAddress;

    @Schema(description = "企业规模")
    private String size;

    @Schema(description = "主营业务")
    private String business;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

}