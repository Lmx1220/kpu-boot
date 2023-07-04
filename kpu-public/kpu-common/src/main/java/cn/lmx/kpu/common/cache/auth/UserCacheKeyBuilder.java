package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #c_user
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class UserCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
