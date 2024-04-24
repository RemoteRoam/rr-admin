package tech.remote.admin.module.business.customer.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.common.json.serializer.DictValueVoSerializer;

/**
 * 客户表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Data
public class CustomerVO {


    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "客户级别")
    @JsonSerialize(using = DictValueVoSerializer.class)
    private String customerLevel;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系人电话")
    private String contactPhone;

    private String province;

    @Schema(description = "省份名称")
    private String provinceName;

    private String city;

    @Schema(description = "城市名称")
    private String cityName;

    private String district;

    @Schema(description = "区县名称")
    private String districtName;

    private String address;

    private String factoryProvince;

    @Schema(description = "工厂省份名称")
    private String factoryProvinceName;

    private String factoryCity;

    @Schema(description = "工厂城市名称")
    private String factoryCityName;

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

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}