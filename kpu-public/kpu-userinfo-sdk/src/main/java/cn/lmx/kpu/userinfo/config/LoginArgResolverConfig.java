package cn.lmx.kpu.userinfo.config;

import cn.lmx.kpu.userinfo.resolver.ContextArgumentResolver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@AllArgsConstructor
@Configuration
public class LoginArgResolverConfig implements WebMvcConfigurer {

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ContextArgumentResolver());
    }

}
