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
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TaskStatus", description = "执行状态  (手机号具体发送状态看sms_send_status表)  #TaskStatus{WAITING:等待执行-枚举")
public enum TaskStatus implements BaseEnum {

    /**
     * DRAFT
     */
    DRAFT("草稿"),
    /**
     * WAITING
     */
    WAITING("等待执行"),
    /**
     * SUCCESS
     */
    SUCCESS("执行成功"),
    /**
     * FAIL
     */
    FAIL("执行失败"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static TaskStatus match(String val, TaskStatus def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TaskStatus get(String val) {
        return match(val, null);
    }

    public boolean eq(TaskStatus val) {
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
