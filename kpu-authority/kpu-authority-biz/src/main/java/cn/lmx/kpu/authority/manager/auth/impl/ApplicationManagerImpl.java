package cn.lmx.kpu.authority.manager.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.authority.dao.auth.ApplicationMapper;
import cn.lmx.kpu.authority.entity.auth.Application;
import cn.lmx.kpu.authority.manager.auth.ApplicationManager;
import cn.lmx.kpu.common.cache.auth.ApplicationCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicationManagerImpl extends SuperCacheManagerImpl<ApplicationMapper, Application> implements ApplicationManager {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }

    @Override
    public Application getByClient(String clientId, String clientSecret) {
        LbqWrapper<Application> wrapper = Wraps.<Application>lbQ()
                .select(Application::getId).eq(Application::getClientId, clientId).eq(Application::getClientSecret, clientSecret);
        Function<CacheKey, Object> loader = k -> super.getObj(wrapper, Convert::toLong);
        CacheKey cacheKey = new ApplicationClientCacheKeyBuilder().key(clientId, clientSecret);
        return getByKey(cacheKey, loader);
    }

}
