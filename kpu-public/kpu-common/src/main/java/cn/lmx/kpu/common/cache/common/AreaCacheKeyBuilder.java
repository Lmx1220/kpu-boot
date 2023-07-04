package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:PARAMETER_KEY:{key} -> value
 * <p>
 * #c_parameter
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class AreaCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.AREA;
    }

}
