package tech.remote.admin.module.business.oa.notice.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.remote.admin.module.business.oa.notice.constant.NoticeVisibleRangeDataTypeEnum;
import tech.remote.base.common.swagger.SchemaEnum;
import tech.remote.base.common.validator.enumeration.CheckEnum;

import javax.validation.constraints.NotNull;

/**
 * 通知公告 可见范围数据
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-12 21:40:39
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVisibleRangeForm {

    @SchemaEnum(NoticeVisibleRangeDataTypeEnum.class)
    @CheckEnum(value = NoticeVisibleRangeDataTypeEnum.class, required = true, message = "数据类型错误")
    private Integer dataType;

    @Schema(description = "员工/部门id")
    @NotNull(message = "员工/部门id不能为空")
    private Long dataId;
}
