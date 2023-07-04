package cn.lmx.kpu.common.cache.auth;

import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 系统用户 KEY
 * <p>
 * #c_user
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class UserAccountCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_ACCOUNT;
    }
}
