package cn.lmx.kpu.file.domain;

import lombok.Builder;
import lombok.Data;
import cn.lmx.kpu.file.enumeration.FileStorageType;

/**
 * 文件删除
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@Builder
public class FileDeleteBO {
    /**
     * 桶
     */
    private String bucket;
    /**
     * 相对路径
     */
    private String path;
    /**
     * 存储类型
     */
    private FileStorageType storageType;
}
