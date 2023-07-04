package cn.lmx.kpu.common.cache.common;


import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * online:{userid} -> token (String)
 * <p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class OnlineCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ONLINE;
    }

    /**
     * 获取通配符
     *
     * @return key 前缀
     */
    @Override
    public String getPattern() {
        return StrUtil.format("{}:{}:*", getTenant(), getPrefix());
    }
}
