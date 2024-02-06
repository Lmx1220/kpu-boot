package cn.lmx.kpu.base.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.base.entity.user.BaseEmployee;
import cn.lmx.kpu.base.mapper.BaseEmployeeTestMapper;
import cn.lmx.kpu.common.cache.base.user.EmployeeCacheKeyBuilder;


/**
 * 仅测试使用
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeTestServiceImpl extends SuperCacheManagerImpl<BaseEmployeeTestMapper, BaseEmployee> implements BaseEmployeeTestService {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new EmployeeCacheKeyBuilder();
    }

    @Override
    public BaseEmployee get(Long id) {
        return baseMapper.get(id);
    }
}
