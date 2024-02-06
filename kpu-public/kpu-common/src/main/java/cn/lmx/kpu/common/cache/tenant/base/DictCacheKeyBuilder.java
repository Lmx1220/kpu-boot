package cn.lmx.kpu.common.cache.tenant.base;


import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.model.cache.CacheHashKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: lc:def_dict:tenant:id:string:{dict_key}
 * field1: {item_key1} --> item_name
 * field2: {item_key2} --> item_name
 *
 * <p>
 * #def_dict
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class DictCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable dictKey) {
        return new DictCacheKeyBuilder().hashKey(dictKey);
    }

    public static CacheHashKey builder(String dictKey, String itemKey) {
        return new DictCacheKeyBuilder().hashFieldKey(itemKey, dictKey);
    }

    @Override
    public String getPrefix() {
        return CacheKeyModular.PREFIX;
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.DICT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
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
