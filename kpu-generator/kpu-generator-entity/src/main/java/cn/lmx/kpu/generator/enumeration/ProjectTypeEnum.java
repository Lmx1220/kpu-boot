package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目类型
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeEnum implements BaseEnum {
    BOOT("单体版"),
    CLOUD("微服务版"),
    ;

    private final String desc;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.name();
    }

    public boolean eq(ProjectTypeEnum val) {
        return val != null && this.getCode().equalsIgnoreCase(val.getCode());
    }
}
