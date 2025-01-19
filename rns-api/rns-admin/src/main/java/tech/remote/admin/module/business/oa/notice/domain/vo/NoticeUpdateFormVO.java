package tech.remote.admin.module.business.oa.notice.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.json.serializer.FileKeyVoSerializer;

import java.util.List;

/**
 * 用于更新 【通知、公告】 的 VO 对象
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-12 21:40:39
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class NoticeUpdateFormVO extends NoticeVO {

    @Schema(description = "纯文本内容")
    private String contentText;

    @Schema(description = "html内容")
    private String contentHtml;

    @Schema(description = "附件")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    private String attachment;

    @Schema(description = "可见范围")
    private List<NoticeVisibleRangeVO> visibleRangeList;

}
