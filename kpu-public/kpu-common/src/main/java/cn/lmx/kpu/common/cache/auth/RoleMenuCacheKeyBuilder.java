package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 资源 KEY
 * <p>
 * #c_role_authority
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class RoleMenuCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ROLE_MENU;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }


}
