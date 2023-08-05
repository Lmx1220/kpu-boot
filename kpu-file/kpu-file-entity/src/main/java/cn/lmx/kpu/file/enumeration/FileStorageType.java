package cn.lmx.kpu.file.enumeration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cn.lmx.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 文件 存储类型 枚举
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileStorageType", description = "文件存储类型")
public enum FileStorageType implements BaseEnum {
    /**
     * 本地
     */
    LOCAL("本地"),
    /**
     * FastDFS
     */
    FAST_DFS("FastDFS"),
    /**
     * minIO
     */
    MIN_IO("MinIO"),
    ALI_OSS("阿里云OSS"),
    QINIU_OSS("七牛云OSS"),
    HUAWEI_OSS("华为云OSS"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static FileStorageType match(String val, FileStorageType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static FileStorageType get(String val) {
        return match(val, null);
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "LOCAL,FAST_DFS,MIN_IO,ALI,QINIU", example = "LOCAL")
    public String getCode() {
        return this.name();
    }


    public boolean eq(FileStorageType type) {
        return type != null && this.name().equals(type.name());
    }
}
