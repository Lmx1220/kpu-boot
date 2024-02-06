package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 参数 KEY
 * {tenant}:TODAY_LOGIN_IV:{now} -> long
 * <p>
 * #c_login_log
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class TodayLoginIvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(LocalDate now) {
        return new TodayLoginIvCacheKeyBuilder().key(now.toString());
    }

    @Override
    public String getTable() {
        return CacheKeyTable.TODAY_LOGIN_IV;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(2L);
    }
}
