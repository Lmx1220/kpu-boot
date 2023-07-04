package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.authority.dao.auth.ApplicationMapper;
import cn.lmx.kpu.authority.entity.auth.Application;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import cn.lmx.kpu.common.cache.auth.ApplicationCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationMapper, Application> implements ApplicationService {

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
