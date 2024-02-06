package cn.lmx.kpu.common.cache.base.system;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 角色的资源 KEY
 * <p>
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class RoleResourceCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(Long applicationId, Long roleId) {
        return new RoleResourceCacheKeyBuilder().key(applicationId, roleId);
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.ROLE_RESOURCE;
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
    public Duration getExpire() {
        return Duration.ofHours(24);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.number;
    }
}
