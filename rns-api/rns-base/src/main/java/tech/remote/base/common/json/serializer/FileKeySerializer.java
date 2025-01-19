package tech.remote.base.common.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.module.support.file.service.FileService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 文件key进行序列化对象
 *
 * @Author 远游工作室: 罗伊
 * @Date 2020/8/15 22:06
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public class FileKeySerializer extends JsonSerializer<String> {

    @Resource
    private FileService fileService;


    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (StringUtils.isEmpty(value)) {
            jsonGenerator.writeString(value);
            return;
        }
        if (fileService == null) {
            jsonGenerator.writeString(value);
            return;
        }
        ResponseDTO<String> responseDTO = fileService.getFileUrl(value);
        if (responseDTO.getOk()) {
            jsonGenerator.writeString(responseDTO.getData());
            return;
        }
        jsonGenerator.writeString(value);
    }
}
