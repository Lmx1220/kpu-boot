package cn.lmx.kpu.file.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.file.enumeration.FileStorageType;
import cn.lmx.kpu.model.enumeration.base.FileType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static cn.lmx.kpu.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_file")
@AllArgsConstructor
public class File extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    @TableField(value = "biz_type")
    private String bizType;

    /**
     * 文件类型
     */
    @TableField(value = "file_type", condition = LIKE)
    private FileType fileType;

    /**
     * 存储类型
     * LOCAL FAST_DFS MIN_IO ALI
     */
    @TableField(value = "storage_type", condition = LIKE)
    private FileStorageType storageType;

    /**
     * 桶
     */
    @TableField(value = "bucket", condition = LIKE)
    private String bucket;

    /**
     * 文件相对地址
     */
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * 文件访问地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;

    /**
     * 唯一文件名
     */
    @TableField(value = "unique_file_name", condition = LIKE)
    private String uniqueFileName;

    /**
     * 文件md5
     */
    @TableField(value = "file_md5", condition = LIKE)
    private String fileMd5;

    /**
     * 原始文件名
     */
    @TableField(value = "original_file_name", condition = LIKE)
    private String originalFileName;

    /**
     * 文件类型
     */
    @TableField(value = "content_type", condition = LIKE)
    private String contentType;

    /**
     * 后缀
     */
    @TableField(value = "suffix", condition = LIKE)
    private String suffix;

    /**
     * 大小
     */
    @TableField(value = "size_")
    private Long size;


    @Builder
    public File(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                String bizType, FileType fileType, FileStorageType storageType, String bucket,
                String path, String url, String uniqueFileName, String fileMd5, String originalFileName, String contentType,
                String suffix, Long size) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.bizType = bizType;
        this.fileType = fileType;
        this.storageType = storageType;
        this.bucket = bucket;
        this.path = path;
        this.url = url;
        this.uniqueFileName = uniqueFileName;
        this.fileMd5 = fileMd5;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.suffix = suffix;
        this.size = size;
    }

}
