package cn.lmx.kpu.file.vo.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import cn.lmx.kpu.file.enumeration.FileStorageType;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 附件上传
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@ApiModel(value = "FileUploadVO", description = "附件上传")
public class FileUploadVO implements Serializable {

    @ApiModelProperty(value = "业务类型")
    @NotBlank(message = "请填写业务类型")
    private String bizType;

    @ApiModelProperty(value = "桶")
    private String bucket;

    @ApiModelProperty(value = "存储类型")
    private FileStorageType storageType;
}
