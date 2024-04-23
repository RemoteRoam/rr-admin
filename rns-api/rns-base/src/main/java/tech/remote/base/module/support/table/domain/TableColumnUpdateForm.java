package tech.remote.base.module.support.table.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 自定义表格列
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-12 22:52:21
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class TableColumnUpdateForm {

    @NotNull(message = "表id不能为空")
    private Integer tableId;

    @NotEmpty(message = "请上传列")
    private List<TableColumnItemForm> columnList;

}
