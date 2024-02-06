package cn.lmx.kpu.file.strategy.impl;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.file.domain.FileGetUrlBO;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.mapper.FileMapper;
import cn.lmx.kpu.file.properties.FileServerProperties;
import cn.lmx.kpu.file.strategy.FileStrategy;
import cn.lmx.kpu.file.utils.FileTypeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.UUID;

import static cn.lmx.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static cn.lmx.basic.utils.DateUtils.SLASH_DATE_FORMAT;


/**
 * 文件抽象策略 处理类
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractFileStrategy implements FileStrategy {

    private static final String FILE_SPLIT = ".";
    protected final FileServerProperties fileProperties;
    protected final FileMapper fileMapper;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 附件
     */
    @Override
    public File upload(MultipartFile multipartFile, String bucket, String bizType) {
        try {
            if (!StrUtil.contains(multipartFile.getOriginalFilename(), FILE_SPLIT)) {
                throw BizException.wrap(BASE_VALID_PARAM.build("文件缺少后缀名"));
            }

            File file = File.builder()
                    .originalFileName(multipartFile.getOriginalFilename())
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .bizType(bizType)
                    .suffix(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                    .fileType(FileTypeUtil.getFileType(multipartFile.getContentType()))
                    .build();
            uploadFile(file, multipartFile, bucket);
            return file;
        } catch (Exception e) {
            log.error("ex", e);
            throw BizException.wrap(BASE_VALID_PARAM.build("文件上传失败"), e);
        }
    }

    /**
     * 具体类型执行上传操作
     *
     * @param file          附件
     * @param multipartFile 文件
     * @param bucket        bucket
     * @throws Exception 异常
     */
    protected abstract void uploadFile(File file, MultipartFile multipartFile, String bucket) throws Exception;

    @Override
    public String getUrl(FileGetUrlBO fileGet) {
        return findUrl(Collections.singletonList(fileGet)).get(fileGet.getPath());
    }

    /**
     * 获取年月日 2024/02/07
     *
     * @return 日期文件夹
     */
    protected String getDateFolder() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(SLASH_DATE_FORMAT));
    }

    /**
     * 企业/年/月/日/业务类型/唯一文件名
     */
    protected String getPath(String bizType, String uniqueFileName) {
        return new StringJoiner(StrPool.SLASH)
                .add(bizType).add(getDateFolder()).add(uniqueFileName).toString();
    }

    protected String getUniqueFileName(File file) {
        return new StringJoiner(StrPool.DOT)
                .add(UUID.randomUUID().toString().replace("-", ""))
                .add(file.getSuffix()).toString();
    }
}
