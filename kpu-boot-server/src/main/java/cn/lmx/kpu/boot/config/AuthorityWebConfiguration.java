package cn.lmx.kpu.boot.config;

import cn.lmx.basic.boot.config.BaseConfig;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.jwt.TokenUtil;
import cn.lmx.basic.log.event.SysLogListener;
import cn.lmx.kpu.authority.service.common.OptLogService;
import cn.lmx.kpu.boot.interceptor.TokenHandlerInterceptor;
import cn.lmx.kpu.common.properties.IgnoreProperties;
import cn.lmx.kpu.common.properties.SystemProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Configuration
@EnableConfigurationProperties({IgnoreProperties.class, SystemProperties.class})
@RequiredArgsConstructor
public class AuthorityWebConfiguration extends BaseConfig implements WebMvcConfigurer {

    private final IgnoreProperties ignoreTokenProperties;
    private final DatabaseProperties databaseProperties;
    private final TokenUtil tokenUtil;
    private final CacheOps cacheOps;
    @Value("${spring.profiles.active:dev}")
    protected String profiles;

    @Bean
    public HandlerInterceptor getTokenHandlerInterceptor() {
        return new TokenHandlerInterceptor(profiles, ignoreTokenProperties, databaseProperties,
                tokenUtil, cacheOps);
    }

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getTokenHandlerInterceptor())
                .addPathPatterns("/**")
                .order(5)
                .excludePathPatterns(commonPathPatterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * auth-client 中的拦截器需要排除拦截的地址
     */
    protected String[] getExcludeCommonPathPatterns() {
        return new String[]{
                "/*.css",
                "/*.js",
                "/*.html",
                "/error",
                "/login",
                "/v2/api-docs",
                "/v2/api-docs-ext",
                "/swagger-resources/**",
                "/webjars/**",

                "/",
                "/csrf",

                "/META-INF/resources/**",
                "/resources/**",
                "/static/**",
                "/public/**",
                "classpath:/META-INF/resources/**",
                "classpath:/resources/**",
                "classpath:/static/**",
                "classpath:/public/**",

                "/cache/**",
                "/swagger-ui.html**",
                "/doc.html**"
        };
    }

    /**
     * kpu.log.enabled = true 并且 kpu.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${kpu.log.enabled:true} && 'DB'.equals('${kpu.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(optLogService::save);
    }
}
