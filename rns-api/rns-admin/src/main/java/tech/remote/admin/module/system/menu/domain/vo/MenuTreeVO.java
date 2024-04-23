package tech.remote.admin.module.system.menu.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 菜单
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-03-06 22:04:37
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class MenuTreeVO extends MenuVO{

    @Schema(description = "菜单子集")
    private List<MenuTreeVO> children;
}
