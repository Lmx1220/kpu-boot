package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.authority.dao.common.AreaMapper;
import cn.lmx.kpu.authority.entity.common.Area;
import cn.lmx.kpu.authority.service.common.AreaService;
import cn.lmx.kpu.common.cache.common.AreaCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

public class AreaServiceImpl extends SuperCacheServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new AreaCacheKeyBuilder();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recursively(List<Long> ids) {
        boolean removeFlag = removeByIds(ids);
        delete(ids);
        return removeFlag;
    }

    private void delete(List<Long> ids) {
        // 查询子节点
        List<Long> childIds = super.listObjs(Wraps.<Area>lbQ().select(Area::getId).in(Area::getParentId, ids), Convert::toLong);
        if (!childIds.isEmpty()) {
            removeByIds(childIds);
            delete(childIds);
        }
        log.debug("退出地区数据递归");
    }
}
