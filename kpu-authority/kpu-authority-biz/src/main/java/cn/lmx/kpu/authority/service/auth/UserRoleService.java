package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveVO;
import cn.lmx.kpu.authority.entity.auth.UserRole;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface UserRoleService extends SuperService<Long, UserRole, UserRole, UserRole, UserRole, UserRole> {
    /**
     * 给用户分配角色
     *
     * @param userRole 用于角色
     * @return 是否成功
     */
    List<Long> saveUserRole(UserRoleSaveVO userRole);

    /**
     * 给角色绑定用户
     *
     * @param roleUser 用于角色
     * @return 是否成功
     */
    List<Long> saveRoleUser(RoleUserSaveVO roleUser);

    /**
     * 根据角色查找用户
     *
     * @param roleId 角色ID
     * @return
     */
    List<Long> findUserIdByRoleId(Long roleId);

    /**
     * 根据用户查询角色
     *
     * @param userId 用户id
     */
    List<Long> findRoleIdByUserId(Long userId);
    /**
     * 初始化超级管理员角色 权限
     *
     * @param userId 用户id
     * @return 是否正确
     */
    boolean initAdmin(Long userId);

}
