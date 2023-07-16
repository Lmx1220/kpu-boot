package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.authority.dao.auth.RoleMapper;
import cn.lmx.kpu.authority.dto.auth.RoleSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUpdateVo;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.RoleAuthority;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.service.auth.RoleAuthorityService;
import cn.lmx.kpu.authority.service.auth.RoleService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.common.cache.auth.*;
import cn.lmx.kpu.security.constant.RoleConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class RoleServiceImpl extends SuperCacheServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleAuthorityService roleAuthorityService;
    private final UserRoleService userRoleService;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new RoleCacheKeyBuilder();
    }

    @Override
    public boolean isPtAdmin(String code) {
        return RoleConstant.SUPER_ADMIN.equals(code);
    }


    /**
     * 删除角色时，需要级联删除跟角色相关的一切资源：
     * 1，角色本身
     * 2，角色-组织：
     * 3，角色-权限（菜单和按钮）：
     * 4，角色-用户：角色拥有的用户
     * 5，用户-权限：
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        // 橘色
        boolean removeFlag = removeByIds(ids);
        // 角色权限
        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getRoleId, ids));

        // 角色绑定了那些用户
        List<Long> userIds = userRoleService.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, ids),
                Convert::toLong);

        //角色拥有的用户
        userRoleService.remove(Wraps.<UserRole>lbQ().in(UserRole::getRoleId, ids));

        cacheOps.del(ids.stream().map(new RoleMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(ids.stream().map(new RoleResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));

        if (!userIds.isEmpty()) {
            //用户角色 、 用户菜单、用户资源
            cacheOps.del(userIds.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(userIds.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return removeFlag;
    }

    /**
     * 1、根据 {tenant}:USER_ROLE:{userId} 查询用户拥有的角色ID集合
     * 2、缓存中有，则根据角色ID集合查询 角色集合
     * 3、缓存中有查不到，则从DB查询，并写入缓存， 立即返回
     *
     * @param userId 用户id
     */
    @Override
    public List<Role> findRoleByUserId(Long userId) {
        CacheKey cacheKey = new UserRoleCacheKeyBuilder().key(userId);
        List<Role> roleList = new ArrayList<>();
        List<Long> list = cacheOps.get(cacheKey, k -> {
            roleList.addAll(baseMapper.findRoleByUserId(userId));
            return roleList.stream().map(Role::getId).collect(Collectors.toList());
        });

        if (!roleList.isEmpty()) {
            roleList.forEach(this::setCache);
            return roleList;
        } else {
            return findByIds(list, this::listByIds);
        }
    }

    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(RoleSaveVO data, Long userId) {
        ArgumentAssert.isFalse(StrUtil.isNotBlank(data.getCode()) && check(data.getCode()), "角色编码{}已存在", data.getCode());
        Role role = BeanPlusUtil.toBean(data, Role.class);
        role.setCode(StrHelper.getOrDef(data.getCode(), RandomUtil.randomString(8)));
        role.setReadonly(false);
        save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateVo data, Long userId) {
        ArgumentAssert.isFalse(StrUtil.isNotBlank(data.getCode()) && check(data.getCode(), data.getId()), "角色编码{}已存在", data.getCode());
        Role role = BeanPlusUtil.toBean(data, Role.class);
        updateById(role);

    }

    @Override
    public List<Long> findUserIdByCode(String[] codes) {
        return baseMapper.findUserIdByCode(codes);
    }

    @Override
    public Boolean check(String code) {
        return super.count(Wraps.<Role>lbQ().eq(Role::getCode, code)) > 0;
    }

    private Boolean check(String code, Long id) {
        return super.count(Wraps.<Role>lbQ().eq(Role::getCode, code).ne(Role::getId, id)) > 0;
    }
}
