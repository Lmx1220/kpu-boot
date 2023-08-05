package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 业务接口
 * 菜单
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface ResourceService extends SuperCacheService<Long, Resource, ResourceSaveVO, ResourceUpdateVo, Resource, ResourceResultVO> {

    /**
     * 根据ID删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return 是否成功
     */
    boolean updateWithCache(Resource menu);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 是否成功
     */
    boolean saveWithCache(Resource menu);

    /**
     * 查询用户可用菜单
     *
     * @param group  组
     * @param userId 用户id
     * @return 菜单
     */
    List<Resource> findVisibleResource(String group, Long userId);

    /**
     * 查询系统所有的菜单和资源树
     *
     * @return java.util.List<cn.lmx.kpu.authority.dto.auth.ResourceResourceTreeVO>
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    List<ResourceTreeVO> findResourceResource(Boolean menuOnly);

    /**
     * 查询系统所有的数据权限
     *
     * @return java.util.List<cn.lmx.kpu.authority.dto.auth.ResourceResourceTreeVO>
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    List<ResourceTreeVO> findResourceDataScopeTree();

    void saveBatchWithCache(List<Resource> list);

    List<AuthDto> findAuthByParentId(Serializable parentId);

    void saveOrUpdateBatchWithCache(List<Resource> list);

    /**
     * 菜单上移
     *
     * @param id
     * @return java.lang.Boolean
     */
    Boolean moveUp(Long id);

    /**
     * 菜单下移
     *
     * @param id
     * @return java.lang.Boolean
     */
    Boolean moveDown(Long id);

    /**
     * 菜单移动
     *
     * @param currentId 当前菜单ID
     * @param targetId  目标菜单ID
     * @return java.lang.Boolean
     */
    Boolean move(Long currentId, Long targetId);
}
