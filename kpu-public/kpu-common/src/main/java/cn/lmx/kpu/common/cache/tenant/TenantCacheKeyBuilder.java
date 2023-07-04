package cn.lmx.kpu.common.cache.tenant;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #d_tenant
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class TenantCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTenant() {
        return StrPool.EMPTY;
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TENANT;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
