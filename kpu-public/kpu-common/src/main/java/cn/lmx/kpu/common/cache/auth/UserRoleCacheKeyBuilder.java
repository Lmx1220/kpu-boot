package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 用户角色 KEY
 * <p>
 * #c_user_role
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class UserRoleCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_ROLE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(12);
    }
}
