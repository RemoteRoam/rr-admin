package tech.remote.admin.module.business.goods.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 盘点表 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class StocktakeVO {


    @Schema(description = "盘点ID")
    private Long id;

    @Schema(description = "盘点单号")
    private String stocktakeNo;

    @Schema(description = "盘点单名称")
    private String title;

    @Schema(description = "商品类目ID")
    private Long categoryId;

    @Schema(description = "删除状态")
    private Integer deletedFlag;

    @Schema(description = "备注")
    private String remark;

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

    @Schema(description = "商品型号规格列表")
    private List<StocktakeItemVO> itemList;
}