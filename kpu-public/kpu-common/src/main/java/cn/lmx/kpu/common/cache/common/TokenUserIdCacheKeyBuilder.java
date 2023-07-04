package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * #c_application
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class TokenUserIdCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TOKEN_USER_ID;
    }

//    @Override
//    public Duration getExpire() {
//        return Duration.ofMinutes(15);
//    }
}
