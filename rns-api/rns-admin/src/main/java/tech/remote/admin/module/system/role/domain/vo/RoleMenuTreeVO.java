package tech.remote.admin.module.system.role.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.admin.module.system.menu.domain.vo.MenuSimpleTreeVO;

import java.util.List;

/**
 * 角色菜单树
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-04-08 21:53:04
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class RoleMenuTreeVO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "菜单列表")
    private List<MenuSimpleTreeVO> menuTreeList;

    @Schema(description = "选中的菜单ID")
    private List<Long> selectedMenuId;
}
