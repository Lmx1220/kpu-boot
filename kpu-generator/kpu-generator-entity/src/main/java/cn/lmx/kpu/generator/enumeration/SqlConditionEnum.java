package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import cn.lmx.kpu.model.constant.Condition;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * SQL 比较条件常量定义类
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SqlConditionEnum", description = "SQL 比较条件常量定义类")
public enum SqlConditionEnum implements BaseEnum {

    /**
     * 等于
     */
    EQUAL("01", "static " + SqlCondition.class.getCanonicalName() + "."),
    /**
     * 不等于
     */
    NOT_EQUAL("02", "static " + SqlCondition.class.getCanonicalName() + "."),
    /**
     * % 两边 %
     */
    LIKE("03", "static " + Condition.class.getCanonicalName() + "."),
    /**
     * % 两边 % [oracle使用]
     */
    ORACLE_LIKE("04", "static " + SqlCondition.class.getCanonicalName() + "."),
    /**
     * % 左
     */
    LIKE_LEFT("05", "static " + SqlCondition.class.getCanonicalName() + "."),
    /**
     * 右 %
     */
    LIKE_RIGHT("06", "static " + SqlCondition.class.getCanonicalName() + "."),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static SqlConditionEnum match(String val, SqlConditionEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static SqlConditionEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(SqlConditionEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.desc + this.name();
    }
}
