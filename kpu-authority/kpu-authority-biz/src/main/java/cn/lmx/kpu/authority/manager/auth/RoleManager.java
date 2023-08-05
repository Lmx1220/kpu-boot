package cn.lmx.kpu.authority.manager.auth;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.authority.dto.auth.RoleSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUpdateVo;
import cn.lmx.kpu.authority.entity.auth.Role;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:05
 */
public interface RoleManager extends SuperCacheManager<Role> {

    /**
     * 根据ID删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 判断用户是否 租户系统的超级管理员
     *
     * @param code 角色编码
     * @return 是否成功
     */
    boolean isPtAdmin(String code);

    /**
     * 查询用户拥有的角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param data   角色
     * @param userId 用户id
     */
    void saveRole(RoleSaveVO data, Long userId);

    /**
     * 修改
     *
     * @param role   角色
     * @param userId 用户id
     */
    void updateRole(RoleUpdateVo role, Long userId);

    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 角色编码
     * @return 用户id
     */
    List<Long> findUserIdByCode(String[] codes);

    /**
     * 检测编码重复
     *
     * @param code 角色编码
     * @return 存在返回真
     */
    Boolean check(String code);
}
