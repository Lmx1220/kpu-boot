package cn.lmx.kpu.system.manager.application;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.system.entity.application.DefResource;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务层
 * 资源
 * </p>
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
public interface DefResourceManager extends SuperCacheManager<DefResource> {
    /**
     * 查找特定应用，拥有的资源
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<cn.lmx.kpu.system.entity.application.DefResource>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<DefResource> findResourceListByApplicationId(List<Long> applicationIdList, Collection<String> resourceTypes);

    /**
     * 根据资源ID和资源类型 查找资源
     *
     * @param idList 资源ID
     * @param types  资源类型
     * @return java.util.List<cn.lmx.kpu.model.entity.system.SysResource>
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    List<DefResource> findByIdsAndType(Collection<? extends Serializable> idList, Collection<String> types);

    /**
     * 查询指定节点的下一级子节点
     * 不会递归查询
     *
     * @param parentId
     * @return
     */
    List<DefResource> findChildrenByParentId(Long parentId);

    /**
     * 查询指定节点的所有子节点
     * 递归查询
     *
     * @param parentId
     * @return
     */
    List<DefResource> findAllChildrenByParentId(Long parentId);
    /**
     * 删除 角色-资源关系表
     *
     * @param resourceIds 资源id
     * @return int
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    int deleteRoleResourceRelByResourceId(List<Long> resourceIds);
    /**
     * 根据应用id查询应用下的资源
     *
     * @param applicationIds applicationId
     * @return java.util.List<cn.lmx.kpu.tenant.entity.tenant.DefResource>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<DefResource> findByApplicationId(List<Long> applicationIds);

}
