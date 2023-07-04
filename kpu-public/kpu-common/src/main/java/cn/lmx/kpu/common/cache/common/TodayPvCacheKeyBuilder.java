package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 参数 KEY
 * {tenant}:TODAY_PV:{now} -> long
 * <p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class TodayPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(LocalDate now) {
        return new TodayPvCacheKeyBuilder().key(now.toString());
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TODAY_PV;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(2L);
    }
}
