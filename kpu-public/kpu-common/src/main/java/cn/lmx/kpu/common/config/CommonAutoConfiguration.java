package cn.lmx.kpu.common.config;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.lmx.kpu.common.aspect.KpuLogAspect;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.properties.SystemProperties;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties(SystemProperties.class)
public class CommonAutoConfiguration {
    private final SystemProperties systemProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = SystemProperties.PREFIX, name = "recordKpu", havingValue = "true", matchIfMissing = true)
    public KpuLogAspect getKpuLogAspect() {
        return new KpuLogAspect(systemProperties);
    }

    @PostConstruct
    public void init() {
        if (StrUtil.isNotEmpty(systemProperties.getCachePrefix())) {
            CacheKeyModular.PREFIX = systemProperties.getCachePrefix();
            log.info("检查到配置文件中：{}.cachePrefix={}", SystemProperties.PREFIX, systemProperties.getCachePrefix());
        }
    }

}
