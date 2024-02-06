package cn.lmx.kpu.file.strategy.impl.minio;

import lombok.extern.slf4j.Slf4j;
import cn.lmx.basic.base.R;
import cn.lmx.kpu.file.dto.chunk.FileChunksMergeDTO;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.mapper.FileMapper;
import cn.lmx.kpu.file.properties.FileServerProperties;
import cn.lmx.kpu.file.strategy.impl.AbstractFileChunkStrategy;

import java.util.List;

/**
 * 欢迎PR
 * <p>
 * 思路1：minIO的putObject自身就支持断点续传， 所以先将分片文件上传到文件服务器并合并成大文件后， 在将大文件通过putObject直接上传到minIO
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
@Slf4j
public class MinIoFileChunkStrategyImpl extends AbstractFileChunkStrategy {
    public MinIoFileChunkStrategyImpl(FileMapper fileMapper, FileServerProperties fileProperties) {
        super(fileMapper, fileProperties);
    }


    @Override
    protected void copyFile(File file) {

    }


    @Override
    protected R<File> merge(List<java.io.File> files, String path, String fileName, FileChunksMergeDTO info) {

        File filePo = File.builder()
//                .relativePath(relativePath)
//                .url(StrUtil.replace(url, "\\\\", StrPool.SLASH))
                .build();
        return R.success(filePo);
    }
}
