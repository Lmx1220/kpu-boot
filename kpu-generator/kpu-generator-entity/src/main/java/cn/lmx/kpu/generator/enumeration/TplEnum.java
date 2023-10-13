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
 * 前端生成页面样式模板
 * </p>
 * #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TplEnum", description = "模板类型")
public enum TplEnum implements BaseEnum {

    /**
     * 单表
     */
    SIMPLE("01", "单表"),
    /**
     * 树结构
     */
    TREE("02", "树结构"),
    MAIN_SUB("03", "主从结构"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TplEnum match(String val, TplEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TplEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TplEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", example = "01")
    public String getCode() {
        return this.name();
    }


}
