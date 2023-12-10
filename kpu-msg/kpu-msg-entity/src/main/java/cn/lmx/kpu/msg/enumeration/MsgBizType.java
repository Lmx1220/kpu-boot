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
@ApiModel(value = "MsgBizType", description = "业务类型  #MsgBizType{USER_LOCK:账号锁定-枚举")
public enum MsgBizType implements BaseEnum {

    /**
     * USER_LOCK
     */
    USER_LOCK("账号锁定"),
    /**
     * USER_REG
     */
    USER_REG("账号申请"),
    /**
     * WORK_APPROVAL
     */
    WORK_APPROVAL("考勤审批"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static MsgBizType match(String val, MsgBizType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgBizType get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgBizType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "name", allowableValues = "USER_LOCK,USER_REG,WORK_APPROVAL", example = "USER_LOCK")
    public String getCode() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
