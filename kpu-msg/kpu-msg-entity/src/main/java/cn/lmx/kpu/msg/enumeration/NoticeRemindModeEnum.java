package cn.lmx.kpu.msg.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息表
 * </p>
 *
 * @author lmx
 * @date 2023/11/19  18:40
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "消息类型-枚举")
public enum NoticeRemindModeEnum implements BaseEnum {

    /**
     * TO_DO="待办"
     */
    TO_DO("01", "待办"),
    /**
     * WARN="预警"
     */
    EARLY_WARNING("02", "预警"),
    /**
     * NOTIFY="提醒"
     */
    NOTICE("03", "提醒"),
    ;

    private String value;
    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static NoticeRemindModeEnum match(String val, NoticeRemindModeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static NoticeRemindModeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(NoticeRemindModeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "TO_DO,NOTICE,EARLY_WARNING", example = "TO_DO")
    public String getCode() {
        return this.value;
    }

}
