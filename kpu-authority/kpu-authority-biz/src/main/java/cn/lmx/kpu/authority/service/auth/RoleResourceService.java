package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.dto.auth.RoleResourceSaveVO;
import cn.lmx.kpu.authority.dto.auth.RoleUserSaveVO;
import cn.lmx.kpu.authority.dto.auth.UserRoleSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleResource;
import cn.lmx.kpu.authority.entity.auth.RoleResource;

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
public interface RoleResourceService extends SuperService<Long, RoleResource, RoleResource, RoleResource, RoleResource, RoleResource> {
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
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleResourceSaveVO 角色授权信息
     * @return 是否成功
     */
    boolean saveRoleResource(RoleResourceSaveVO roleResourceSaveVO);

    /**
     * 根据权限id 删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByResourceId(List<Long> ids);

}
