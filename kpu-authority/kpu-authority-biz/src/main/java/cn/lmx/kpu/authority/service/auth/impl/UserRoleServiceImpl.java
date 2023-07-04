package cn.lmx.kpu.authority.service.auth.impl;


import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.exception.BizException;
import cn.lmx.kpu.authority.dao.auth.RoleMapper;
import cn.lmx.kpu.authority.dao.auth.UserRoleMapper;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service

@RequiredArgsConstructor
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final RoleMapper roleMapper;

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

        return super.save(userRole);
    }
}
