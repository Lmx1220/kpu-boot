package cn.lmx.kpu.common.cache.base.user;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 岗位 KEY
 * <p>
 * #base_position
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:岗位id] -> obj
 * base:base_position:id:obj:1 -> {}
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class PositionCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.BASE_POSITION;
    }

    @Override
    public String getPrefix() {
        return CacheKeyModular.PREFIX;
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
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
