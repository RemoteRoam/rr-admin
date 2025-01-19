package tech.remote.base.module.support.config.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;
import org.hibernate.validator.constraints.Length;

/**
 * 分页查询 系统配置
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-14 20:46:27
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class ConfigQueryForm extends PageParam {

    @Schema(description = "参数KEY")
    @Length(max = 50, message = "参数Key最多50字符")
    private String configKey;
}
