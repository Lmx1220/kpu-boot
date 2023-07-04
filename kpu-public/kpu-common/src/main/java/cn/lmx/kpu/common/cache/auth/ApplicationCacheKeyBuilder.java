package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 应用 KEY
 * <p>
 * #c_auth_application
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class ApplicationCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.APPLICATION;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
