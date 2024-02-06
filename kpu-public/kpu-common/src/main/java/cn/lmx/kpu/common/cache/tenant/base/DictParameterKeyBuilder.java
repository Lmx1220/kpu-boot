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
 * key: lc:def_parameter:tenant:id:string:{id}
 * value: obj
 *
 * <p>
 * #def_parameter
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class DictParameterKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable id) {
        return new DictParameterKeyBuilder().hashKey(id);
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
        return CacheKeyTable.System.DEF_PARAMETER;
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
