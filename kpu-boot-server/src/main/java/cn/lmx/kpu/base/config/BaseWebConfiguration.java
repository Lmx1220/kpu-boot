package cn.lmx.kpu.base.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cn.lmx.basic.boot.config.BaseConfig;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.jwt.TokenHelper;
import cn.lmx.basic.log.event.SysLogListener;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.base.interceptor.AuthenticationFilter;
import cn.lmx.kpu.base.interceptor.TokenContextFilter;
import cn.lmx.kpu.base.service.system.BaseOperationLogService;
import cn.lmx.kpu.base.vo.save.system.BaseOperationLogSaveVO;
import cn.lmx.kpu.common.properties.IgnoreProperties;
import cn.lmx.kpu.common.properties.SystemProperties;
import cn.lmx.kpu.oauth.biz.ResourceBiz;

/**
 * 基础服务-Web配置
 *
 * @author lmx
 * @date 2024/02/07
 */
@Configuration
@EnableConfigurationProperties({IgnoreProperties.class, SystemProperties.class})
@RequiredArgsConstructor
public class BaseWebConfiguration extends BaseConfig implements WebMvcConfigurer {


    private final IgnoreProperties ignoreProperties;
    private final TokenHelper tokenUtil;
    private final CacheOps cacheOps;
    private final ResourceBiz oauthResourceBiz;
    @Value("${spring.profiles.active:dev}")
    protected String profiles;

    @Bean
    public HandlerInterceptor getTokenContextFilter() {
        return new TokenContextFilter(profiles, ignoreProperties, tokenUtil, cacheOps);
    }

    @Bean
    public HandlerInterceptor getAuthenticationFilter() {
        return new AuthenticationFilter(ignoreProperties, oauthResourceBiz);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getTokenContextFilter())
                .addPathPatterns("/**")
                .order(5)
                .excludePathPatterns(commonPathPatterns);
        registry.addInterceptor(getAuthenticationFilter())
                .addPathPatterns("/**")
                .order(10)
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
    public SysLogListener sysLogListener(BaseOperationLogService logApi) {
        return new SysLogListener(data -> logApi.save(BeanPlusUtil.toBean(data, BaseOperationLogSaveVO.class)));
    }
}
