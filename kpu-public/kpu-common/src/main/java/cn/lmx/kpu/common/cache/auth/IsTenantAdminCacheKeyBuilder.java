package cn.lmx.kpu.common.cache.auth;


import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyModular;
import cn.lmx.kpu.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 员工 是否系统管理员
 * <p>
 * 完整key: ${companyId}:is_sys_admin:${employeeId} -> "1" or "0"
 * <p>
 *
 * @author lmx
 * @date 2024/02/07  02:04 下午
 */
public class IsTenantAdminCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().key(employeeId);
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
    public String getModular() {
        return CacheKeyModular.COMMON;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.TENANT;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
