package tech.remote.admin.module.system.datascope.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 数据范围
 *
 * @Author 远游工作室: 罗伊
 * @Date 2020/11/28  20:59:17
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
@Builder
public class DataScopeViewTypeVO {

    @Schema(description = "可见范围")
    private Integer viewType;

    @Schema(description = "可见范围名称")
    private String viewTypeName;

    @Schema(description = "级别,用于表示范围大小")
    private Integer viewTypeLevel;
}
