package tech.remote.base.module.support.file.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import tech.remote.base.common.code.UserErrorCode;
import tech.remote.base.common.constant.StringConst;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartEnumUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.util.SmartStringUtil;
import tech.remote.base.module.support.file.constant.FileFolderTypeEnum;
import tech.remote.base.module.support.file.dao.FileDao;
import tech.remote.base.module.support.file.domain.entity.FileEntity;
import tech.remote.base.module.support.file.domain.form.FileQueryForm;
import tech.remote.base.module.support.file.domain.vo.FileDownloadVO;
import tech.remote.base.module.support.file.domain.vo.FileUploadVO;
import tech.remote.base.module.support.file.domain.vo.FileVO;
import tech.remote.base.module.support.redis.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文件服务
 *
 * @Author 远游工作室: 罗伊
 * @Date 2019年10月11日 15:34:47
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class FileService {

    /**
     * 文件名最大长度
     */
    private static final int FILE_NAME_MAX_LENGTH = 100;

    @Resource
    private IFileStorageService fileStorageService;

    @Resource
    private FileDao fileDao;

    @Resource
    private RedisService redisService;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;


    /**
     * 文件上传服务
     *
     * @param file
     * @param folderType 文件夹类型
     * @return
     */
    public ResponseDTO<FileUploadVO> fileUpload(MultipartFile file, Integer folderType, RequestUser requestUser) {
        FileFolderTypeEnum folderTypeEnum = SmartEnumUtil.getEnumByValue(folderType, FileFolderTypeEnum.class);
        if (null == folderTypeEnum) {
            return ResponseDTO.userErrorParam("文件夹错误");
        }

        if (null == file || file.getSize() == 0) {
            return ResponseDTO.userErrorParam("上传文件不能为空");
        }

        // 校验文件名称
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            return ResponseDTO.userErrorParam("上传文件名称不能为空");
        }

        if (originalFilename.length() > FILE_NAME_MAX_LENGTH) {
            return ResponseDTO.userErrorParam("文件名称最大长度为：" + FILE_NAME_MAX_LENGTH);
        }

        // 校验文件大小
        String maxSizeStr = maxFileSize.toLowerCase().replace("mb", "");
        long maxSize = Integer.parseInt(maxSizeStr) * 1024 * 1024L;
        if (file.getSize() > maxSize) {
            return ResponseDTO.userErrorParam("上传文件最大为:" + maxSize);
        }

        // 进行上传
        ResponseDTO<FileUploadVO> response = fileStorageService.upload(file, folderTypeEnum.getFolder());
        if (!response.getOk()) {
            return response;
        }

        // 上传成功 保存记录数据库
        FileUploadVO uploadVO = response.getData();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFolderType(folderTypeEnum.getValue());
        fileEntity.setFileName(originalFilename);
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileKey(uploadVO.getFileKey());
        fileEntity.setFileType(uploadVO.getFileType());
        fileEntity.setCreatorId(requestUser == null ? null : requestUser.getUserId());
        fileEntity.setCreatorName(requestUser == null ? null : requestUser.getUserName());
        fileEntity.setCreatorUserType(requestUser == null ? null : requestUser.getUserType().getValue());
        fileDao.insert(fileEntity);

        // 将fileId 返回给前端
        uploadVO.setFileId(fileEntity.getFileId());

        return response;
    }

    /**
     * 批量获取文件信息
     *
     * @param fileKeyList
     * @return
     */
    public List<FileVO> getFileList(List<String> fileKeyList) {
        if (CollectionUtils.isEmpty(fileKeyList)) {
            return Lists.newArrayList();
        }

        // 查询数据库，并获取 file url
        HashSet<String> fileKeySet = new HashSet<>(fileKeyList);
        Map<String, FileVO> fileMap = fileDao.selectByFileKeyList(fileKeySet)
                .stream().collect(Collectors.toMap(FileVO::getFileKey, Function.identity()));

        for (FileVO fileVO : fileMap.values()) {
            ResponseDTO<String> fileUrlResponse = fileStorageService.getFileUrl(fileVO.getFileKey());
            if (fileUrlResponse.getOk()) {
                fileVO.setFileUrl(fileUrlResponse.getData());
            }
        }

        // 返回结果
        List<FileVO> result = Lists.newArrayListWithCapacity(fileKeyList.size());
        for (String fileKey : fileKeyList) {
            FileVO fileVO = fileMap.get(fileKey);
            if (fileVO != null) {
                result.add(fileVO);
            }
        }

        return result;
    }


    /**
     * 根据文件绝对路径 获取文件URL
     * 支持单个 key 逗号分隔的形式
     *
     * @param fileKeys
     * @return
     */
    public ResponseDTO<String> getFileUrl(String fileKeys) {
        if (StringUtils.isBlank(fileKeys)) {
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR);
        }

        List<String> fileKeyArray = StrUtil.split(fileKeys, StringConst.SEPARATOR);
        List<String> fileUrlList = Lists.newArrayListWithCapacity(fileKeyArray.size());
        for (String fileKey : fileKeyArray) {
            ResponseDTO<String> fileUrlResponse = fileStorageService.getFileUrl(fileKey);
            if (fileUrlResponse.getOk()) {
                fileUrlList.add(fileUrlResponse.getData());
            }
        }
        return ResponseDTO.ok(SmartStringUtil.join(StringConst.SEPARATOR, fileUrlList));
    }


    /**
     * 根据文件服务类型 和 FileKey 下载文件
     */
    public ResponseDTO<FileDownloadVO> getDownloadFile(String fileKey, String userAgent) {
        FileVO fileVO = fileDao.getByFileKey(fileKey);
        if (fileVO == null) {
            return ResponseDTO.userErrorParam("文件不存在");
        }

        // 根据文件服务类 获取对应文件服务 查询 url
        ResponseDTO<FileDownloadVO> download = fileStorageService.download(fileKey);
        if(download.getOk()){
            download.getData().getMetadata().setFileName(fileVO.getFileName());
        }
        return download;
    }

    /**
     * 分页查询
     */
    public PageResult<FileVO> queryPage(FileQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<FileVO> list = fileDao.queryPage(page, queryForm);
        return SmartPageUtil.convert2PageResult(page, list);
    }


}
