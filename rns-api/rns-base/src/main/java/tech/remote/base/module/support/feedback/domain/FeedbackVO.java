package tech.remote.base.module.support.feedback.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.remote.base.common.enumeration.UserTypeEnum;
import tech.remote.base.common.json.deserializer.FileKeyVoDeserializer;
import tech.remote.base.common.json.serializer.FileKeyVoSerializer;
import tech.remote.base.common.swagger.SchemaEnum;

import java.time.LocalDateTime;

/**
 * 意见反馈 返回对象
 *
 * @Author 远游工作室: 开云
 * @Date 2022-08-11 20:48:09
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class FeedbackVO {

    @Schema(description = "主键")
    private Long feedbackId;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String feedbackAttachment;

    @Schema(description = "创建人id")
    private Long userId;

    @Schema(description = "创建人姓名")
    private String userName;

    @SchemaEnum(value = UserTypeEnum.class, desc = "创建人类型")
    private Integer userType;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}