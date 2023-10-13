package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 弹窗方式
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PopupTypeEnum", description = "弹窗方式")
public enum PopupTypeEnum implements BaseEnum {

    /**
     * 单表
     */
    MODAL("01", "对话框"),
    /**
     * 树结构
     */
    DRAWER("02", "抽屉"),
    JUMP("03", "跳转"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static PopupTypeEnum match(String val, PopupTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static PopupTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(PopupTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", example = "01")
    public String getCode() {
        return this.name();
    }


}
