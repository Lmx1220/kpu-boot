package cn.lmx.kpu.authority.service.auth.impl;


import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dao.auth.RoleMapper;
import cn.lmx.kpu.authority.dao.auth.UserRoleMapper;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveVO;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.UserRoleManager;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.BizConstant.INIT_ROLE_CODE;

/**
 * <p>
 * 业务实现类
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleManager, Long, UserRole, UserRole, UserRole, UserRole, UserRole> implements UserRoleService {

    private final RoleMapper roleMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveRoleUser(RoleUserSaveVO saveVO) {
        ArgumentAssert.notEmpty(saveVO.getUserIdList(), "请选择用户");
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, saveVO.getRoleId()).in(UserRole::getUserId, saveVO.getUserIdList()));
        if (saveVO.getFlag()) {
            List<UserRole> list = saveVO.getUserIdList().stream().map(employeeId ->
                    UserRole.builder().userId(employeeId).roleId(saveVO.getRoleId()).build()).collect(Collectors.toList());
            saveBatch(list);
        }
        return findUserIdByRoleId(saveVO.getRoleId());
    }

    @Override
    public List<Long> findUserIdByRoleId(Long roleId) {
        return superManager.listObjs(Wraps.<UserRole>lbQ()
                        .select(UserRole::getUserId).eq(UserRole::getRoleId, roleId),
                Convert::toLong);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveUserRole(UserRoleSaveVO saveVO) {
        ArgumentAssert.notEmpty(saveVO.getRoleIdList(), "请选择角色");
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        remove(Wraps.<UserRole>lbQ().eq(UserRole::getUserId, saveVO.getUserId()).in(UserRole::getRoleId, saveVO.getRoleIdList()));
        if (saveVO.getFlag()) {
            List<UserRole> list = saveVO.getRoleIdList().stream().map(roleId ->
                    UserRole.builder().roleId(roleId).userId(saveVO.getUserId()).build()).collect(Collectors.toList());
            saveBatch(list);
        }
        return findRoleIdByUserId(saveVO.getUserId());
    }

    @Override
    public List<Long> findRoleIdByUserId(Long userId) {
        return superManager.listObjs(Wraps.<UserRole>lbQ()
                        .select(UserRole::getRoleId).eq(UserRole::getUserId, userId),
                Convert::toLong);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initAdmin(Long userId) {
        Role role = roleMapper.selectOne(Wraps.<Role>lbQ().eq(Role::getCode, INIT_ROLE_CODE));
        if (role == null) {
            throw BizException.wrap("初始化用户角色失败, 无法查询到内置角色:%s", INIT_ROLE_CODE);
        }
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(role.getId())
                .build();

        return superManager.save(userRole);
    }
}
