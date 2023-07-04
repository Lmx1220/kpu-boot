package cn.lmx.kpu.common.cache.core;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 部门 KEY
 * <p>
 * #c_org
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class OrgCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ORG;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
