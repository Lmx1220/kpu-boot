package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.entity.auth.UserRole;

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
public interface UserRoleService extends SuperService<UserRole> {
    /**
     * 初始化超级管理员角色 权限
     *
     * @param userId 用户id
     * @return 是否正确
     */
    boolean initAdmin(Long userId);
}
