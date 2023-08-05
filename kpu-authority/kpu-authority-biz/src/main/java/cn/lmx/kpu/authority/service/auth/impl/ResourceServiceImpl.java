package cn.lmx.kpu.authority.service.auth.impl;

import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.Resource;
import cn.lmx.kpu.authority.manager.auth.ResourceManager;
import cn.lmx.kpu.authority.service.auth.ResourceService;
import cn.lmx.kpu.authority.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 菜单
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ResourceServiceImpl extends SuperCacheServiceImpl<ResourceManager, Long, Resource, ResourceSaveVO, ResourceUpdateVo, Resource, ResourceResultVO> implements ResourceService {

    /**
     * 查询用户可用菜单
     * 1，查询缓存中存放的当前用户拥有的所有菜单列表 [resourceId,resourceId...]
     * 2，缓存&DB为空则返回
     * 3，缓存总用户菜单列表 为空，但db存在，则将list便利set到缓存，并直接返回
     * 4，缓存存在用户菜单列表，则根据菜单id遍历去缓存查询菜单。
     * 5，过滤group后，返回
     *
     * <p>
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param group  分组
     * @param userId 用户id
     * @return 指定用户的拥有的菜单
     */
    @Override
    @Transactional(readOnly = true)
    public List<Resource> findVisibleResource(String group, Long userId) {
        return superManager.findVisibleResource(group, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        return superManager.removeByIdWithCache(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWithCache(Resource resource) {
        return superManager.updateWithCache(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWithCache(Resource resource) {
        return superManager.saveWithCache(resource);
    }
    @Override
    @Transactional(readOnly = true)
    public List<ResourceTreeVO> findResourceResource(Boolean resourceOnly) {
        return superManager.findResourceResource(resourceOnly);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourceTreeVO> findResourceDataScopeTree() {
        return superManager.findResourceDataScopeTree();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchWithCache(List<Resource> list) {
        superManager.saveBatchWithCache(list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthDto> findAuthByParentId(Serializable parentId) {
        return superManager.findAuthByParentId(parentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateBatchWithCache(List<Resource> list) {
        superManager.saveOrUpdateBatchWithCache(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean moveUp(Long id) {
        return superManager.moveUp(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean moveDown(Long id) {
        return superManager.moveDown(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean move(Long currentId, Long targetId) {
        return superManager.move(currentId, targetId);
    }

}
