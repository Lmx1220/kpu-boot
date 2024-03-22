package cn.lmx.kpu.base.manager.system.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.kpu.base.entity.system.BaseRoleResourceRel;
import cn.lmx.kpu.base.manager.system.BaseRoleResourceRelManager;
import cn.lmx.kpu.base.mapper.system.BaseRoleResourceRelMapper;
import cn.lmx.kpu.common.cache.base.system.RoleResourceCacheKeyBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseRoleResourceRelManagerImpl extends SuperManagerImpl<BaseRoleResourceRelMapper, BaseRoleResourceRel> implements BaseRoleResourceRelManager {
    private final CacheOps cacheOps;

    @Override
    public List<BaseRoleResourceRel> findByRoleIdAndCategory(Long roleId, String category) {
        return baseMapper.findByRoleIdAndCategory(roleId, category);
    }

    @Override
    public void deleteByRole(Collection<Long> roleIdList) {
        List<BaseRoleResourceRel> roleResourceRelList = list(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));
        super.remove(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));

        List<CacheKey> keys = new ArrayList<>();
        for (BaseRoleResourceRel rr : roleResourceRelList) {
            keys.add(RoleResourceCacheKeyBuilder.build(rr.getApplicationId(), rr.getRoleId()));
            keys.add(RoleResourceCacheKeyBuilder.build(null, rr.getRoleId()));

        }
        cacheOps.del(keys);
    }
}
