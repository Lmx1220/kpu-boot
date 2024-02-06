package cn.lmx.kpu.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import cn.lmx.basic.interfaces.BaseEnum;

/**
 * 项目类型
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeEnum implements BaseEnum {
    /**
     * 单体版
     */
    BOOT("单体版"),
    /**
     * 微服务版
     */
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
