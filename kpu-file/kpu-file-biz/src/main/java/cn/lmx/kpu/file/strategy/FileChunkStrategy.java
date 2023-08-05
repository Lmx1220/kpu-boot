package cn.lmx.kpu.file.strategy;


import cn.lmx.basic.base.R;
import cn.lmx.kpu.file.dto.chunk.FileChunksMergeDTO;
import cn.lmx.kpu.file.entity.File;

/**
 * 文件分片处理策略类
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface FileChunkStrategy {

    /**
     * 根据md5检测文件
     *
     * @param md5    md5
     * @param userId 用户id
     * @return 附件
     */
    File md5Check(String md5, Long userId);

    /**
     * 合并文件
     *
     * @param merge 合并参数
     * @return 附件
     */
    R<File> chunksMerge(FileChunksMergeDTO merge);
}
