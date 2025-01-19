package tech.remote.admin.module.business.third.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 三方机构 分页查询表单
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Data
public class ThirdPartyQueryForm extends PageParam{

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "三方类型")
    private String type;

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

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

}