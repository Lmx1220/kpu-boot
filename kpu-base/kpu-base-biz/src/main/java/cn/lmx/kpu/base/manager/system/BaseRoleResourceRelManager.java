package cn.lmx.kpu.base.manager.system;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.base.entity.system.BaseRoleResourceRel;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseRoleResourceRelManager extends SuperManager<BaseRoleResourceRel> {

    /**
     * 根据角色id和角色类别，查询角色拥有的权限
     *
     * @param roleId   角色ID
     * @param category 角色类别
     * @return
     */
    List<BaseRoleResourceRel> findByRoleIdAndCategory(Long roleId, String category);

    /**
     * 根据角色ID删除角色的资源
     *
     * @param roleIdList idList
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    void deleteByRole(Collection<Long> roleIdList);
}
