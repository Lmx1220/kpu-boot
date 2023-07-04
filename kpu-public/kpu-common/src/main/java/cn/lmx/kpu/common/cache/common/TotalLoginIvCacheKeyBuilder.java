package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:TOTAL_LOGIN_IV -> long
 * <p>
 * #c_login_log
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class TotalLoginIvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TodayLoginIvCacheKeyBuilder().key();
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TOTAL_LOGIN_IV;
    }
}
