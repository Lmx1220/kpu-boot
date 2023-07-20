package cn.lmx.kpu.model.enumeration.base;

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
 * 机构类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginStatusEnum", description = "登录状态-枚举")
public enum LoginStatusEnum implements BaseEnum {

    /**
     * 登录成功
     */
    SUCCESS("01", "登录成功"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR("02", "验证码错误"),
    /**
     * 账号密码错误
     */
    PWD_ERROR("03", "账号密码错误"),
    /**
     * 账号异常
     */
    ACCOUNT_ERROR("04", "账号异常"),
    /**
     * 切换租户
     */
    SWITCH_TENANTS("05", "切换租户"),
    /**
     * 短信验证码错误
     */
    SMS_CAPTCHA_ERROR("06", "短信验证码错误"),
    ;

    private String code;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static LoginStatusEnum match(String val, LoginStatusEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static LoginStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(LoginStatusEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "10,20", example = "10")
    public String getCode() {
        return this.code;
    }


}
