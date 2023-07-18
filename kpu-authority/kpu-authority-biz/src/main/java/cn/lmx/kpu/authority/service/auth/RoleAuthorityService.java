package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.dto.auth.RoleResourceSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleAuthority;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface RoleAuthorityService extends SuperService<RoleAuthority> {
    /**
     * 根据用户-角色-数据权限关系，查询可用的数据权限ID
     * <p>
     * 角色被禁用后，用户不在拥有此角色的权限
     *
     * @param userId   用户ID
     * @param category 角色类别
     * @return
     */
    List<Long> selectDataScopeIdFromRoleByUserId(Long userId, String category);

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
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleResourceSaveVO 角色授权信息
     * @return 是否成功
     */
    boolean saveRoleAuthority(RoleResourceSaveVO roleResourceSaveVO);

    /**
     * 根据权限id 删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByAuthorityId(List<Long> ids);

}