package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dao.auth.MenuMapper;
import cn.lmx.kpu.authority.dao.auth.RoleAuthorityMapper;
import cn.lmx.kpu.authority.dto.auth.RoleAuthoritySaveDTO;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveDTO;
import cn.lmx.kpu.authority.entity.auth.RoleAuthority;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.service.auth.RoleAuthorityService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.common.cache.auth.RoleMenuCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.RoleResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserMenuCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
@Service

@RequiredArgsConstructor
public class RoleAuthorityServiceImpl extends SuperServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {

    private final UserRoleService userRoleService;
    private final MenuMapper menuMapper;
    private final CacheOps cacheOps;

    @Override
    public List<Long> selectDataScopeIdFromRoleByUserId(Long userId, String category) {
        return baseMapper.selectDataScopeIdFromRoleByUserId(userId, category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveRoleUser(RoleUserSaveVO saveVO) {
        ArgumentAssert.notEmpty(saveVO.getUserIdList(), "请选择用户");
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, saveVO.getRoleId()).in(UserRole::getUserId, saveVO.getUserIdList()));
        if (saveVO.getFlag()) {
            List<UserRole> list = saveVO.getUserIdList().stream().map(employeeId ->
                    UserRole.builder().userId(employeeId).roleId(saveVO.getRoleId()).build()).collect(Collectors.toList());
            userRoleService.saveBatch(list);
        }
        return findUserIdByRoleId(saveVO.getRoleId());
    }

    @Override
    public List<Long> findUserIdByRoleId(Long roleId) {
        return userRoleService.listObjs(Wraps.<UserRole>lbQ()
                        .select(UserRole::getUserId).eq(UserRole::getRoleId, roleId),
                Convert::toLong);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserRole(UserRoleSaveDTO userRole) {
        List<UserRole> oldUserRoleList = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, userRole.getRoleId()));
        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, userRole.getRoleId()));

        Set<Long> delIdList = new HashSet<>();
        if (CollUtil.isNotEmpty(userRole.getUserIdList())) {
            List<UserRole> list = userRole.getUserIdList()
                    .stream()
                    .map(userId -> UserRole.builder()
                            .userId(userId)
                            .roleId(userRole.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            userRoleService.saveBatch(list);
            delIdList.addAll(userRole.getUserIdList());
        }

        /* 角色A -> u1 u2
            修改成
            角色A -> u3 u2
            所以， 应该清除 u1、u2、u3 的角色、菜单、资源
            */

        if (!oldUserRoleList.isEmpty()) {
            delIdList.addAll(oldUserRoleList.stream().map(UserRole::getUserId).collect(Collectors.toSet()));
        }
        delUserAuthority(delIdList);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleAuthority(RoleAuthoritySaveDTO dto) {
        ArgumentAssert.notNull(dto.getRoleId(), "请选择角色");

        //删除角色和资源的关联
        super.remove(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, dto.getRoleId()));

        List<RoleAuthority> list = new ArrayList<>();
        if (dto.getMenuIdList() != null && !dto.getMenuIdList().isEmpty()) {
            //保存授予的菜单
            List<RoleAuthority> menuList = new HashSet<>(dto.getMenuIdList())
                    .stream()
                    .map(menuId -> RoleAuthority.builder()
                            .authorityId(menuId)
                            .roleId(dto.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(menuList);
        }
        super.saveBatch(list);

        // 角色下的所有用户
        List<Long> userIdList = userRoleService.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).eq(UserRole::getRoleId, dto.getRoleId()),
                Convert::toLong);

        delUserAuthority(userIdList);
        cacheOps.del(new RoleResourceCacheKeyBuilder().key(dto.getRoleId()));
        cacheOps.del(new RoleMenuCacheKeyBuilder().key(dto.getRoleId()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByAuthorityId(List<Long> ids) {
        List<Long> roleIds = listObjs(
                Wraps.<RoleAuthority>lbQ().select(RoleAuthority::getRoleId).in(RoleAuthority::getAuthorityId, ids),
                Convert::toLong);

        if (!roleIds.isEmpty()) {
            remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getAuthorityId, ids));

            List<Long> userIdList = userRoleService.listObjs(
                    Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, roleIds),
                    Convert::toLong);

            delUserAuthority(userIdList);
            cacheOps.del(roleIds.stream().map(new RoleResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(roleIds.stream().map(new RoleMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return true;
    }

    private void delUserAuthority(Collection<Long> collections) {
        cacheOps.del(collections.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(collections.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
    }
}
