package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  09:53
 */
@Getter
@AllArgsConstructor
public enum SqlConditionEnum implements BaseEnum {
    /**
     * 等于
     */
    EQUAL("EQUAL"),
    /**
     * 不等于
     */
    NOT_EQUAL("NOT_EQUAL"),
    /**
     * 像
     */
    LIKE("LIKE"),

    ORACLE_LIKE("ORACLE_LIKE"),
    /**
     * 左像
     */
    LIKE_LEFT("LIKE_LEFT"),
    /**
     * 右像
     */
    LIKE_RIGHT("LIKE_RIGHT");

    /**
     * 编码
     */
    private String value;

    @Override
    public String getDesc() {
        return value;
    }

    @Override
    public String getCode() {
        return value;
    }
}
