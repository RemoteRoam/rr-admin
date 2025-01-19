package tech.remote.base.module.support.file.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.swagger.SchemaEnum;
import tech.remote.base.common.validator.enumeration.CheckEnum;
import tech.remote.base.module.support.file.constant.FileFolderTypeEnum;

import javax.validation.constraints.NotBlank;

/**
 * url上传文件
 *
 * @Author 远游工作室: 罗伊
 * @Date 2019年10月11日 15:34:47
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class FileUrlUploadForm {

    @SchemaEnum(value = FileFolderTypeEnum.class, desc = "业务类型")
    @CheckEnum(value = FileFolderTypeEnum.class, required = true, message = "业务类型错误")
    private Integer folder;

    @Schema(description = "文件url")
    @NotBlank(message = "文件url不能为空")
    private String fileUrl;

}
