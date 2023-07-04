package cn.lmx.kpu.common.cache;


import cn.lmx.basic.model.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * 短信验证码 KEY
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class VerificationCodeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.REGISTER_USER;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofSeconds(5 * 60);
    }
}
