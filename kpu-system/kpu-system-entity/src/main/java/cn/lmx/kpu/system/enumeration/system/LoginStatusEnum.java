package cn.lmx.kpu.system.enumeration.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cn.lmx.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录状态-枚举")
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

    PASSWORD_ERROR("03", "账号密码错误"),
    /**
     * 账号异常
     */
    USER_ERROR("04", "账号异常"),
    /**
     * 切换租户
     */
    SWITCH("05", "切换租户"),
    /**
     * 短信验证码错误
     */
    SMS_CODE_ERROR("06", "短信验证码错误"),
    ;

    @Schema(description = "code")
    private String code;
    @Schema(description = "描述")
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
    public String getCode() {
        return this.code;
    }
}
