package cn.lmx.kpu.common.cache.gateway;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 阻止列表 KEY
 * <p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class BlockListCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.BLOCKLIST;
    }

}
