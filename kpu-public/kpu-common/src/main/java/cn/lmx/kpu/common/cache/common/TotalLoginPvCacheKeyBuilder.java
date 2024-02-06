package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyTable;

/**
 * 参数 KEY
 * {tenant}:TOTAL_LOGIN_PV -> long
 * <p>
 * #c_login_log
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class TotalLoginPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TotalLoginPvCacheKeyBuilder().key();
    }

    @Override
    public String getTable() {
        return CacheKeyTable.TOTAL_LOGIN_PV;
    }
}
