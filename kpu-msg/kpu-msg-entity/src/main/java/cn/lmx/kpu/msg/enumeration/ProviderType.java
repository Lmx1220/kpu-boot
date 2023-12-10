package cn.lmx.kpu.msg.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 短信模板
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProviderType", description = "供应商类型  #ProviderType{ALI:OK,阿里云短信-枚举")
public enum ProviderType implements BaseEnum {
    /**
     * TENCENT="0","腾讯云短信",
     */
    ALI("OK", "阿里云短信", "\\$\\{([^\\}]+)\\}"),
    /**
     * 腾讯
     */
    TENCENT("0", "腾讯云短信", "\\{([^\\}]+)\\}"),
    /**
     * 百度
     */
    BAIDU("1000", "百度云短信", "\\$\\{([^\\}]+)\\}"),

    CL("0", "创蓝", "\\{([^\\}]+)\\}"),
    ;

    @ApiModelProperty(value = "数据库存储值")
    private String value;
    @ApiModelProperty(value = "描述")
    private String desc;
    private String regex;

    /**
     * 根据当前枚举的name匹配
     */
    public static ProviderType match(String val, ProviderType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ProviderType get(String val) {
        return match(val, null);
    }

    public boolean eq(ProviderType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "ALI,TENCENT,BAIDU", example = "ALI")
    public String getCode() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.value;
    }


    @Override
    public String getExtra() {
        return this.getRegex();
    }

    public SendStatus getTaskStatus(String code) {
        if (this.value.equalsIgnoreCase(code)) {
            return SendStatus.SUCCESS;
        } else {
            return SendStatus.FAIL;
        }
    }
}
