package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.cache.repository.CachePlusOps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.authority.dto.auth.Online;
import cn.lmx.kpu.authority.service.auth.OnlineService;
import cn.lmx.kpu.authority.service.common.ParameterService;
import cn.lmx.kpu.common.cache.common.OnlineCacheKeyBuilder;
import cn.lmx.kpu.common.cache.common.TokenUserIdCacheKeyBuilder;
import cn.lmx.kpu.common.constant.BizConstant;
import cn.lmx.kpu.common.constant.ParameterKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * token
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class OnlineServiceImpl implements OnlineService {

    private final ParameterService parameterService;
    private final CachePlusOps cacheOps;

    @Override
    public List<Online> list(String nickName) {
        String pattern = new OnlineCacheKeyBuilder().getPattern();
        List<String> keys = cacheOps.scan(pattern);

        return keys.stream()
                .map(key -> (Online) cacheOps.get(key))
                .filter(ObjectUtil::isNotEmpty).filter(item ->
                        StrUtil.isEmpty(nickName) || StrUtil.contains(item.getNickName(), nickName)
                ).collect(Collectors.toList());
    }

    /**
     * 1，ONLY_ONE_CLIENT: 一个用户在一个应用 只能登录一次（如一个用户只能在一个APP上登录，也只能在一个PC端登录，但能同时登录PC和APP）
     * 2，MANY: 用户可以任意登录： token -> userid
     * 3，ONLY_ONE: 一个用户只能登录一次
     *
     * @param model 用户token
     * @return 是否成功
     */
    @Override
    public boolean save(Online model) {
        String loginPolicy = parameterService.getValue(ParameterKey.LOGIN_POLICY, ParameterKey.LoginPolicy.MANY.name());
        if (ParameterKey.LoginPolicy.ONLY_ONE.eq(loginPolicy)) {
            // online:1  === {model}
            CacheKey key = new OnlineCacheKeyBuilder().key(model.getUserId());
            setToken(model, key);
        } else if (ParameterKey.LoginPolicy.ONLY_ONE_CLIENT.eq(loginPolicy)) {
            // online:{userid}:{client} === {model}
            CacheKey key = new OnlineCacheKeyBuilder().key(model.getClientId(), model.getUserId());
            setToken(model, key);
        } else {
            CacheKey key = new OnlineCacheKeyBuilder().key(model.getUserId());
            key.setExpire(Duration.ofSeconds(model.getExpireMillis()));
            cacheOps.set(key, model);
        }

        // TOKEN_USER_ID:{token} === 1
        CacheKey tokenKey = new TokenUserIdCacheKeyBuilder().key(model.getToken());
        tokenKey.setExpire(Duration.ofSeconds(model.getExpireMillis()));
        cacheOps.set(tokenKey, String.valueOf(model.getUserId()));
        return true;
    }

    @Override
    public boolean clear(String token, Long userId, String clientId) {
        String loginPolicy = parameterService.getValue(ParameterKey.LOGIN_POLICY, ParameterKey.LoginPolicy.MANY.name());
        CacheKey key;
        if (ParameterKey.LoginPolicy.ONLY_ONE.eq(loginPolicy)) {
            // USER_TOKEN:1  === {model}
            key = new OnlineCacheKeyBuilder().key(userId);

        } else if (ParameterKey.LoginPolicy.ONLY_ONE_CLIENT.eq(loginPolicy)) {
            // USER_TOKEN:{userid}:{client} === {model}
            key = new OnlineCacheKeyBuilder().key(clientId, userId);
        } else {
            key = new OnlineCacheKeyBuilder().key(userId);
        }
        cacheOps.del(key);

        evictPreviousToken(key);
        CacheKey tokenKey = new TokenUserIdCacheKeyBuilder().key(token);
        cacheOps.del(tokenKey);
        return true;
    }

    private void setToken(Online model, CacheKey key) {
        key.setExpire(Duration.ofSeconds(model.getExpireMillis()));
        evictPreviousToken(key);
        cacheOps.set(key, model);
    }

    private void evictPreviousToken(CacheKey key) {
        Online online = cacheOps.get(key, false);
        if (online != null && StrUtil.isNotEmpty(online.getToken())) {
            String previousToken = online.getToken();
            // TOKEN_USER_ID:{token} === T
            CacheKey cacheKey = new TokenUserIdCacheKeyBuilder().key(previousToken);
            cacheOps.set(cacheKey, BizConstant.LOGIN_STATUS);
        }
    }

    @Override
    public boolean reset() {
        CacheKey pattern = new OnlineCacheKeyBuilder().key(StrPool.STAR);
        Set<String> keys = cacheOps.keys(pattern.getKey());
        cacheOps.del(keys.toArray(new String[0]));

        CacheKey cacheKey = new TokenUserIdCacheKeyBuilder().key(StrPool.STAR);
        Set<String> tokenUserIdKeys = cacheOps.keys(cacheKey.getKey());
        cacheOps.del(tokenUserIdKeys.toArray(new String[0]));
        return true;
    }

}
