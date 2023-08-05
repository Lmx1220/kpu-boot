package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 用户菜单 KEY
 * <p>
 * #c_role_authority & #c_user_role
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class UserAuthCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_AUTH;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(12);
    }
}
