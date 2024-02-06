package cn.lmx.kpu.common.cache.base.common;


import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.model.cache.CacheHashKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: dict:{dict_key}
 * field1: {item_key1} --> item_name
 * field2: {item_key2} --> item_name
 *
 * <p>
 * #c_dictionary_item
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class BaseDictCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable dictKey) {
        return new BaseDictCacheKeyBuilder().hashKey(dictKey);
    }

    public static CacheHashKey builder(String dictKey, String field) {
        return new BaseDictCacheKeyBuilder().hashFieldKey(field, dictKey);
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getPrefix() {
        return CacheKeyModular.PREFIX;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.BASE_DICT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.BASE;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }
}
