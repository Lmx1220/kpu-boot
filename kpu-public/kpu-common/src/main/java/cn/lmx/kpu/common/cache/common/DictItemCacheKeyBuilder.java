package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * key={tenant}:dictionary_type:{type}
 * field1: {code} --> name
 * field2: {code} --> name
 *
 * <p>
 * #c_dictionary_item
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class DictItemCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.DICTIONARY_TYPE;
    }


}
