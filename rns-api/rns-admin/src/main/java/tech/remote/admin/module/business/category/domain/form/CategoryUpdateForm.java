package tech.remote.admin.module.business.category.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类目 更新
 *
 * @Author 远游工作室: 胡克
 * @Date 2021/08/05 21:26:58
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class CategoryUpdateForm extends CategoryAddForm {

    @Schema(description = "类目id")
    @NotNull(message = "类目id不能为空")
    private Long categoryId;
}
