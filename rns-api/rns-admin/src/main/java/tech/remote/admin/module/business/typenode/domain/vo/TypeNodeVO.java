package tech.remote.admin.module.business.typenode.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 项目类型节点关系表 列表VO
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Data
public class TypeNodeVO {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "项目类型")
    private Integer projectType;

    @Schema(description = "节点ID")
    private Long nodeId;

    @Schema(description = "节点排序")
    private Integer nodeSort;

}