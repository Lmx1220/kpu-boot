package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dto.auth.RoleResourceSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.RoleResourceManager;
import cn.lmx.kpu.authority.service.auth.RoleResourceService;
import cn.lmx.kpu.authority.service.auth.RoleResourceService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.common.cache.auth.RoleMenuCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.RoleResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleResourceServiceImpl extends SuperServiceImpl<RoleResourceManager, Long, RoleResource, RoleResource, RoleResource, RoleResource, RoleResource> implements RoleResourceService {
    @Override
    public List<Long> selectDataScopeIdFromRoleByUserId(Long userId, String category) {
        return superManager.selectDataScopeIdFromRoleByUserId(userId, category);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleResource(RoleResourceSaveVO dto) {
        return superManager.saveRoleResource(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByResourceId(List<Long> ids) {
        return superManager.removeByResourceId(ids);
    }
}
