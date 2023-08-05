package cn.lmx.kpu.file.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 附件
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "FileGetUrlVO", description = "附件查询")
public class FileGetUrlBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请传入文件路径")
    private String path;
    private String originalFileName;

    private String bucket;
}
