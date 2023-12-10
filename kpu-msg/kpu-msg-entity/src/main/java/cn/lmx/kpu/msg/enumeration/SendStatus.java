package cn.lmx.kpu.msg.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 短信发送状态
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SendStatus", description = "发送状态  #SendStatus{WAITING:等待发送-枚举")
public enum SendStatus implements BaseEnum {

    /**
     * WAITING
     */
    WAITING("等待发送"),
    /**
     * SUCCESS
     */
    SUCCESS("发送成功"),
    /**
     * FAIL
     */
    FAIL("发送失败"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static SendStatus match(String val, SendStatus def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static SendStatus get(String val) {
        return match(val, null);
    }

    public boolean eq(SendStatus val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "WAITING,SUCCESS,FAIL", example = "WAITING")
    public String getCode() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
