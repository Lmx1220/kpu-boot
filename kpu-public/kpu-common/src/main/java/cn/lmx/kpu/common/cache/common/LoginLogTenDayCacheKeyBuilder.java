package cn.lmx.kpu.common.cache.common;


import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:LOGIN_LOG_TEN_DAY -> long
 * <p>
 * #c_login_log
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class LoginLogTenDayCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.LOGIN_LOG_TEN_DAY;
    }

}
