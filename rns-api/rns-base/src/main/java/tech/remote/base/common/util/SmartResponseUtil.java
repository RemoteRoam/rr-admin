package tech.remote.base.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import tech.remote.base.common.domain.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 返回工具栏
 *
 * @Author 远游工作室-yy
 * @Date 2023/11/25 18:51:32
 *
 *
 * @Copyright <a href="http://www.remotenomad.tech">远游工作室</a>，Since 2012
 */

@Slf4j
public class SmartResponseUtil {

    public static void write(HttpServletResponse response, ResponseDTO<?> responseDTO) {
        // 重置response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write(JSON.toJSONString(responseDTO));
            response.flushBuffer();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public static void setDownloadFileHeader(HttpServletResponse response, String fileName) {
        setDownloadFileHeader(response, fileName, null);
    }

    public static void setDownloadFileHeader(HttpServletResponse response, String fileName, Long fileSize) {
        response.setCharacterEncoding("utf-8");
        try {
            if (fileSize != null) {
                response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize));
            }

            if (SmartStringUtil.isNotEmpty(fileName)) {
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaTypeFactory.getMediaType(fileName).orElse(MediaType.APPLICATION_OCTET_STREAM) + ";charset=utf-8");
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"));
                response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
