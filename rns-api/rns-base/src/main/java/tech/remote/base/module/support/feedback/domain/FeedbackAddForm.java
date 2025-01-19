package tech.remote.base.module.support.feedback.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.json.deserializer.FileKeyVoDeserializer;
import tech.remote.base.common.json.serializer.FileKeyVoSerializer;

import javax.validation.constraints.NotBlank;

/**
 * 意见反馈 添加表单
 *
 * @Author 远游工作室: 开云
 * @Date 2022-08-11 20:48:09
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class FeedbackAddForm {

    @Schema(description = "反馈内容")
    @NotBlank(message = "反馈内容不能为空")
    private String feedbackContent;

    @Schema(description = "反馈图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String feedbackAttachment;

}
