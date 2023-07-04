package cn.lmx.kpu.security.properties;

import cn.lmx.basic.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".security";
    /**
     * 是否启用uri权限
     */
    private Boolean enabled = true;
    /**
     * 权限是否区分大小写
     */
    private Boolean caseSensitive = false;
}
