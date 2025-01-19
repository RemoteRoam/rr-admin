package tech.remote.base.module.support.file.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.domain.PageParam;
import tech.remote.base.common.swagger.SchemaEnum;
import tech.remote.base.common.validator.enumeration.CheckEnum;
import tech.remote.base.module.support.file.constant.FileFolderTypeEnum;

import java.time.LocalDate;

/**
 * 文件信息查询
 *
 * @Author 远游工作室: 罗伊
 * @Date 2019年10月11日 15:34:47
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class FileQueryForm extends PageParam {

    @SchemaEnum(value = FileFolderTypeEnum.class, desc = "文件夹类型")
    @CheckEnum(value = FileFolderTypeEnum.class, message = "文件夹类型 错误")
    private Integer folderType;

    @Schema(description = "文件名词")
    private String fileName;

    @Schema(description = "文件Key")
    private String fileKey;

    @Schema(description = "文件类型")
    private String fileType;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

}
