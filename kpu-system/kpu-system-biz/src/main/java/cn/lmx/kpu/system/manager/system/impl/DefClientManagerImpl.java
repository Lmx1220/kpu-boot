package cn.lmx.kpu.system.manager.system.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.cache.redis2.CacheResult;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.common.cache.tenant.system.DefClientCacheKeyBuilder;
import cn.lmx.kpu.common.cache.tenant.system.DefClientSecretCacheKeyBuilder;
import cn.lmx.kpu.system.entity.system.DefClient;
import cn.lmx.kpu.system.manager.system.DefClientManager;
import cn.lmx.kpu.system.mapper.system.DefClientMapper;

/**
 * <p>
 * 通用业务实现类
 * 客户端
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefClientManagerImpl extends SuperCacheManagerImpl<DefClientMapper, DefClient> implements DefClientManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new DefClientCacheKeyBuilder();
    }

    @Override
    public DefClient getClient(String clientId, String clientSecret) {
        CacheKey key = DefClientSecretCacheKeyBuilder.builder(clientId, clientSecret);
        CacheResult<Long> result = cacheOps.get(key, k -> {
            DefClient one = getOne(Wraps.<DefClient>lbQ().eq(DefClient::getClientId, clientId).eq(DefClient::getClientSecret, clientSecret));
            return one == null ? null : one.getId();
        });
        Long id = result.asLong();
        ArgumentAssert.notNull(id, "客户端[{}]不存在", clientId);
        return getByIdCache(id);
    }
}
