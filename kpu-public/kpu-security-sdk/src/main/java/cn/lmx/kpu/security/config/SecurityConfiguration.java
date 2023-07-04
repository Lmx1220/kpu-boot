package cn.lmx.kpu.security.config;

import cn.lmx.kpu.security.aspect.UriSecurityPreAuthAspect;
import cn.lmx.kpu.security.aspect.VerifyAuthFunction;
import cn.lmx.kpu.security.properties.SecurityProperties;
import cn.lmx.kpu.userinfo.feign.UserResolverService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * 权限认证配置类
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Order
@AllArgsConstructor
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfiguration {
    private final SecurityProperties securityProperties;

    @Bean
    @ConditionalOnProperty(prefix = SecurityProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public UriSecurityPreAuthAspect uriSecurityPreAuthAspect(VerifyAuthFunction verifyAuthFunction) {
        return new UriSecurityPreAuthAspect(verifyAuthFunction);
    }

    @Bean("fun")
    @ConditionalOnMissingBean(VerifyAuthFunction.class)
    public VerifyAuthFunction getVerifyAuthFunction(UserResolverService userResolverService) {
        return new VerifyAuthFunction(userResolverService, securityProperties);
    }

}
