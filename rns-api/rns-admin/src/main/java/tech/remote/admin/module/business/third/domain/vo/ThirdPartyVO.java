package tech.remote.admin.module.business.third.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.common.json.serializer.DictValueVoSerializer;

/**
 * 三方机构 列表VO
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Data
public class ThirdPartyVO {


    @Schema(description = "三方ID")
    private Long id;

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "三方类型")
    @JsonSerialize(using = DictValueVoSerializer.class)
    private String type;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系人电话")
    private String contactPhone;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "省份名称")
    private String provinceName;

    @Schema(description = "市")
    private String city;

    @Schema(description = "城市名称")
    private String cityName;

    @Schema(description = "区县")
    private String district;

    @Schema(description = "区县名称")
    private String districtName;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "禁用状态")
    private Integer disabledFlag;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

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