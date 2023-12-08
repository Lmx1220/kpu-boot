package cn.lmx.kpu.sms.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SourceType", description = "发送渠道 #SourceType{APP:应用-枚举")
public enum SourceType implements BaseEnum {

    /**
     * APP
     */
    APP("应用"),
    /**
     * SERVICE
     */
    SERVICE("服务"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static SourceType match(String val, SourceType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static SourceType get(String val) {
        return match(val, null);
    }

    public boolean eq(SourceType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "APP,SERVICE", example = "APP")
    public String getCode() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
