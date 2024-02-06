package cn.lmx.kpu.base.manager.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.base.entity.user.BasePosition;
import cn.lmx.kpu.base.manager.user.BasePositionManager;
import cn.lmx.kpu.base.mapper.user.BasePositionMapper;
import cn.lmx.kpu.common.cache.base.user.PositionCacheKeyBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 通用业务实现类
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BasePositionManagerImpl extends SuperCacheManagerImpl<BasePositionMapper, BasePosition> implements BasePositionManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new PositionCacheKeyBuilder();
    }

    @Transactional(readOnly = true)
    @Override
    
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        List<BasePosition> list = findByIds(ids, null).stream().filter(Objects::nonNull).toList();
        return CollHelper.uniqueIndex(list, BasePosition::getId, BasePosition::getName);
    }
}
