package tech.remote.base.module.support.dict.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022/5/26 19:40:55
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class DictValueVO {

    @Schema(description = "valueId")
    private Long dictValueId;

    @Schema(description = "dictKeyId")
    private Long dictKeyId;

    @Schema(description = "编码")
    private String valueCode;

    @Schema(description = "名称")
    private String valueName;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}