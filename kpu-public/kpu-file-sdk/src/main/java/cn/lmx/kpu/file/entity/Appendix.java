package cn.lmx.kpu.file.entity;

import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
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
 * 业务附件
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27] [lmx] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_appendix")
@AllArgsConstructor
public class Appendix extends Entity<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 业务id
     */
    @TableField(value = "biz_id")
    private Long bizId;

    /**
     * 业务类型
     */
    @TableField(value = "biz_type", condition = LIKE)
    private String bizType;

    /**
     * 文件类型
     */
    @TableField(value = "file_type", condition = LIKE)
    @Echo(api = Echo.ENUM_API)
    private FileType fileType;

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
     * 大小
     */
    @TableField(value = "size_")
    private Long size;


    @Builder
    public Appendix(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                    Long bizId, String bizType, FileType fileType, String bucket, String path,
                    String originalFileName, String contentType, Long size) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.fileType = fileType;
        this.bucket = bucket;
        this.path = path;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.size = size;
    }

}
