package cn.lmx.kpu.authority.manager.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dao.auth.RoleResourceMapper;
import cn.lmx.kpu.authority.dao.auth.UserRoleMapper;
import cn.lmx.kpu.authority.dto.auth.RoleResourceSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.RoleResourceManager;
import cn.lmx.kpu.authority.manager.auth.UserRoleManager;
import cn.lmx.kpu.common.cache.auth.RoleMenuCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.RoleResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RoleResourceManagerImpl extends SuperManagerImpl<RoleResourceMapper, RoleResource> implements RoleResourceManager {

    private final UserRoleManager userRoleManager;
    private final CacheOps cacheOps;

    @Override
    public List<Long> selectDataScopeIdFromRoleByUserId(Long userId, String category) {
        return baseMapper.selectDataScopeIdFromRoleByUserId(userId, category);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleResource(RoleResourceSaveVO dto) {
        ArgumentAssert.notNull(dto.getRoleId(), "请选择角色");

        //删除角色和资源的关联
        super.remove(Wraps.<RoleResource>lbQ().eq(RoleResource::getRoleId, dto.getRoleId()));

        List<RoleResource> list = new ArrayList<>();
        if (dto.getMenuIdList() != null && !dto.getMenuIdList().isEmpty()) {
            //保存授予的菜单
            List<RoleResource> menuList = new HashSet<>(dto.getMenuIdList())
                    .stream()
                    .map(menuId -> RoleResource.builder()
                            .resourceId(menuId)
                            .roleId(dto.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(menuList);
        }
        super.saveBatch(list);

        // 角色下的所有用户
        List<Long> userIdList = userRoleManager.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).eq(UserRole::getRoleId, dto.getRoleId()),
                Convert::toLong);

        delUserResource(userIdList);
        cacheOps.del(new RoleResourceCacheKeyBuilder().key(dto.getRoleId()));
        cacheOps.del(new RoleMenuCacheKeyBuilder().key(dto.getRoleId()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByResourceId(List<Long> ids) {
        List<Long> roleIds = listObjs(
                Wraps.<RoleResource>lbQ().select(RoleResource::getRoleId).in(RoleResource::getResourceId, ids),
                Convert::toLong);

        if (!roleIds.isEmpty()) {
            remove(Wraps.<RoleResource>lbQ().in(RoleResource::getResourceId, ids));

            List<Long> userIdList = userRoleManager.listObjs(
                    Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, roleIds),
                    Convert::toLong);

            delUserResource(userIdList);
            cacheOps.del(roleIds.stream().map(new RoleResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(roleIds.stream().map(new RoleMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return true;
    }

    private void delUserResource(Collection<Long> collections) {
        cacheOps.del(collections.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(collections.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
    }

}
