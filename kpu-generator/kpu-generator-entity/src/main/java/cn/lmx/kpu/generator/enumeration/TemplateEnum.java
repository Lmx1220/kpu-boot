package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum TemplateEnum implements BaseEnum {
    BACKEND("BACKEND", "后端"),
    WEB_PLUS("WEB_PLUS", "前端");

    private String value;
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static TemplateEnum match(String val, TemplateEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TemplateEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TemplateEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码")
    public String getCode() {
        return this.name();
    }

}
