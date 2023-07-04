package cn.lmx.kpu.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 登录配置
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Setter
@Getter
@RefreshScope
@ConfigurationProperties(prefix = SystemProperties.PREFIX)
public class SystemProperties {
    public static final String PREFIX = "kpu.system";
    /**
     * 登录时 是否验证密码， 开发环境使用
     */
    private Boolean verifyPassword = true;
    /**
     * 登录时 是否验证验证码， 开发环境使用
     */
    private Boolean verifyCaptcha = true;
    /**
     * 密码最大输错次数  小于0不限制
     */
    private Integer maxPasswordErrorNum = 10;
    /**
     * 密码错误锁定用户时间
     * <p>
     * 0: 今天结束
     * 1m: 1分钟后
     * 1h: 1小时后
     * 4d: 4天后
     * 2w: 2周后
     * 3M: 3个月后
     * 5y: 5年后
     */
    private String passwordErrorLockUserTime = "0";

}
