package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 参数 KEY
 * <p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class CaptchaCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.CAPTCHA;
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofMinutes(15);
    }
}
