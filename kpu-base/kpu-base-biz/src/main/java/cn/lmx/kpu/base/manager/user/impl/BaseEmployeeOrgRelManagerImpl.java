package cn.lmx.kpu.base.manager.user.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.cache.redis2.CacheResult;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbQueryWrap;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.base.entity.user.BaseEmployeeOrgRel;
import cn.lmx.kpu.base.manager.user.BaseEmployeeOrgRelManager;
import cn.lmx.kpu.base.mapper.user.BaseEmployeeOrgRelMapper;
import cn.lmx.kpu.base.mapper.user.BaseOrgMapper;
import cn.lmx.kpu.common.cache.base.user.EmployeeOrgCacheKeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 员工所在部门
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseEmployeeOrgRelManagerImpl extends SuperManagerImpl<BaseEmployeeOrgRelMapper, BaseEmployeeOrgRel> implements BaseEmployeeOrgRelManager {
    private final CacheOps cacheOps;
    private final BaseOrgMapper orgMapper;

    @Override
    public List<Long> findOrgIdByEmployeeId(Long employeeId) {
        CacheKey eoKey = EmployeeOrgCacheKeyBuilder.build(employeeId);
        CacheResult<List<Long>> orgIdResult = cacheOps.get(eoKey, k -> orgMapper.selectOrgByEmployeeId(employeeId));
        return orgIdResult.asList();
    }

    @Override
    public boolean removeByEmployeeIds(Collection<Long> employeeIds) {
        ArgumentAssert.notEmpty(employeeIds, "员工ID不能为空");

        boolean remove = remove(Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getEmployeeId, employeeIds));
        cacheOps.del(employeeIds.stream().map(EmployeeOrgCacheKeyBuilder::build).toList());
        return remove;
    }

    @Override
    public void deleteByOrg(Collection<Long> orgIdList) {
        if (CollUtil.isEmpty(orgIdList)) {
            return;
        }
        LbQueryWrap<BaseEmployeeOrgRel> wrap = Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getOrgId, orgIdList);
        List<BaseEmployeeOrgRel> list = list(wrap);
        remove(wrap);
        List<CacheKey> keys = list.stream()
                .map(BaseEmployeeOrgRel::getEmployeeId)
                .distinct()
                .map(EmployeeOrgCacheKeyBuilder::build)
                .toList();
        cacheOps.del(keys);
    }
}
