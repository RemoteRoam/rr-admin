package tech.remote.base.module.support.file.domain.vo;

import lombok.Data;

/**
 * 文件下载
 *
 * @Author 远游工作室: 罗伊
 * @Date 2019年10月11日 15:34:47
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class FileDownloadVO {

    /**
     * 文件字节数据
     */
    private byte[] data;

    /**
     * 文件元数据
     */
    private FileMetadataVO metadata;


}
