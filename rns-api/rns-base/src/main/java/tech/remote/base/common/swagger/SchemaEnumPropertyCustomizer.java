package tech.remote.base.common.swagger;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.oas.models.media.Schema;
import tech.remote.base.common.enumeration.BaseEnum;
import tech.remote.base.common.validator.enumeration.CheckEnum;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 *
 * 自定义枚举类文档
 *
 * @Author 远游工作室-yy
 * @Date 2023/12/25 23:28:51
 *  
 *  
 * @Copyright <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
public class SchemaEnumPropertyCustomizer implements PropertyCustomizer {

    @Override
    public Schema customize(Schema schema, AnnotatedType type) {
        if (type.getCtxAnnotations() == null) {
            return schema;
        }

        StringBuilder description = new StringBuilder();
        for (Annotation ctxAnnotation : type.getCtxAnnotations()) {
            if (ctxAnnotation.annotationType().equals(CheckEnum.class) && ((CheckEnum) ctxAnnotation).required()) {
                description.append("<font style=\"color: red;\">【必填】</font>");
            }
        }

        for (Annotation ctxAnnotation : type.getCtxAnnotations()) {
            if (ctxAnnotation.annotationType().equals(SchemaEnum.class)) {
                description.append(((SchemaEnum) ctxAnnotation).desc());
                Class<? extends BaseEnum> clazz = ((SchemaEnum) ctxAnnotation).value();
                description.append(BaseEnum.getInfo(clazz));
            }
        }

        if (description.length() > 0) {
            schema.setDescription(description.toString());
        }
        return schema;
    }

}
