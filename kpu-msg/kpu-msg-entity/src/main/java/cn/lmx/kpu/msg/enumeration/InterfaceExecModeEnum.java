package cn.lmx.kpu.msg.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/11  22:25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "InterfaceExecModeEnum", description = "业务类型-枚举")
public enum InterfaceExecModeEnum implements BaseEnum {

    /**
     * IMPL_CLASS
     */
    IMPL_CLASS("01","实现类"),
    /**
     * SCRIPT
     */
    SCRIPT("02","脚本"),
    ;


    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static InterfaceExecModeEnum match(String val, InterfaceExecModeEnum interfaceExecModeEnum) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(interfaceExecModeEnum);
    }

    public static InterfaceExecModeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(InterfaceExecModeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "IMPL_CLASS,SCRIPT", example = "IMPL_CLASS")
    public String getCode() {
        return this.code;
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.code;
    }
}
