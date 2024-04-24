package tech.remote.admin.module.business.third.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import tech.remote.base.common.json.deserializer.DictValueVoDeserializer;

/**
 * 三方机构 更新表单
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Data
public class ThirdPartyUpdateForm {

    @Schema(description = "三方ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "三方ID 不能为空")
    private Long id;

    @Schema(description = "公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "公司名称 不能为空")
    private String name;

    @Schema(description = "三方类型")
    @JsonDeserialize(using = DictValueVoDeserializer.class)
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

    @Schema(description = "更新人")
    private Long updateUserId;

    @Schema(description = "更新人姓名")
    private String updateUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}