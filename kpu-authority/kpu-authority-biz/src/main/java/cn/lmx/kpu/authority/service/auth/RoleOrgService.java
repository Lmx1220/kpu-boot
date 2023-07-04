package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperService;
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
public interface RoleOrgService extends SuperService<RoleOrg> {

    /**
     * 根据角色id查询
     *
     * @param roleId 角色id
     * @return 组织Id
     */
    List<Long> listOrgByRoleId(Long roleId);
}
