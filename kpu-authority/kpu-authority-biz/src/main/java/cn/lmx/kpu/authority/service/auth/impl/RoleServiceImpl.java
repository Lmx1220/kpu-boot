package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.authority.dto.auth.RolePageQuery;
import cn.lmx.kpu.authority.dto.auth.RoleResultVO;
import cn.lmx.kpu.authority.dto.auth.RoleSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUpdateVo;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.RoleManager;
import cn.lmx.kpu.authority.service.auth.RoleResourceService;
import cn.lmx.kpu.authority.service.auth.RoleResourceService;
import cn.lmx.kpu.authority.service.auth.RoleService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.common.cache.auth.*;
import cn.lmx.kpu.security.constant.RoleConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends SuperCacheServiceImpl<RoleManager, Long, Role, RoleSaveVO, RoleUpdateVo, RolePageQuery, RoleResultVO> implements RoleService {

    @Override
    public boolean isPtAdmin(String code) {
        return superManager.isPtAdmin(code);
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
        return superManager.removeByIdWithCache(ids);
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
        return superManager.findRoleByUserId(userId);
    }

    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(RoleSaveVO data, Long userId) {
        superManager.saveRole(data, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateVo data, Long userId) {
        superManager.updateRole(data, userId);

    }

    @Override
    public List<Long> findUserIdByCode(String[] codes) {
        return superManager.findUserIdByCode(codes);
    }

    @Override
    public Boolean check(String code) {
        return superManager.check(code);
    }

}
