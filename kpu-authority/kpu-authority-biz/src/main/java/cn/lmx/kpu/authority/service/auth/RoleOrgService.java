package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.dto.auth.RoleOrgSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleOrg;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色组织关系
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface RoleOrgService extends SuperService<Long, RoleOrg, RoleOrg, RoleOrg, RoleOrg, RoleOrg> {

    /**
     * 根据角色id查询
     *
     * @param roleId 角色id
     * @return 组织Id
     */
    List<Long> listOrgByRoleId(Long roleId);

    /**
     * 根据组织id查询
     *
     * @param orgId 组织id
     * @return 角色Id
     */
    List<Long> findRoleByOrgId(Long orgId);

    /**
     * 给角色绑定组织
     *
     * @param roleUser 用于角色
     * @return 是否成功
     */
    List<Long> saveRoleOrg(RoleOrgSaveVO roleUser);
}
