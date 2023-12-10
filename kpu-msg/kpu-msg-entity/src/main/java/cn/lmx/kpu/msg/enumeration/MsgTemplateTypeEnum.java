package cn.lmx.kpu.msg.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MsgTemplateTypeEnum", description = "消息类型  #MsgTemplateTypeEnum{WAIT:待办-枚举")
public enum MsgTemplateTypeEnum implements BaseEnum {

    /**
     * WAIT
     */
    WAIT("待办"),
    /**
     * NOTIFY
     */
    NOTIFY("通知"),
    /**
     * PUBLICITY
     */
    PUBLICITY("公告"),
    /**
     * WARN
     */
    WARN("预警"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static MsgTemplateTypeEnum match(String val, MsgTemplateTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgTemplateTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgTemplateTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "WAIT,NOTIFY,PUBLICITY,WARN", example = "WAIT")
    public String getCode() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
