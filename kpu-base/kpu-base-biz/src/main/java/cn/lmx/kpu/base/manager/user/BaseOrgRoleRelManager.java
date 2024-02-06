package cn.lmx.kpu.base.manager.user;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.base.entity.user.BaseOrgRoleRel;

import java.util.Collection;

/**
 * <p>
 * 通用业务接口
 * 组织的角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseOrgRoleRelManager extends SuperManager<BaseOrgRoleRel> {

    /**
     * 根据机构ID删除机构的角色
     *
     * @param idList idList
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    void deleteByOrg(Collection<Long> idList);

    /**
     * 根据角色ID，删除机构的角色
     *
     * @param idList idList
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    void deleteByRole(Collection<Long> idList);
}
