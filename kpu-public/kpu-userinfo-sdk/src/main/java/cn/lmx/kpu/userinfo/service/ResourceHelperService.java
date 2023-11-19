package cn.lmx.kpu.userinfo.service;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.cache.auth.ResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserAuthCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserResourceCacheKeyBuilder;
import cn.lmx.kpu.model.entity.base.SysResource;
import cn.lmx.kpu.model.vo.query.ResourceQueryDTO;
import cn.lmx.kpu.userinfo.dao.ResourceHelperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class ResourceHelperService {
    private final ResourceHelperMapper menuHelperMapper;
    private final CacheOps cacheOps;

    protected void setCache(SysResource model) {
        Object id = model.getId();
        if (id != null) {
            CacheKey key = new ResourceCacheKeyBuilder().key(id);
            cacheOps.set(key, model);
        }
    }

    /**
     * 查询用户的可用资源
     * <p>
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param resource 资源对象
     * @return 用户的可用资源
     */
    public List<SysResource> findVisibleAuth(ResourceQueryDTO resource) {
        ContextUtil.setDatabaseBase();
        CacheKey userResourceKey = new UserAuthCacheKeyBuilder().key(resource.getUserId());
        if (resource.getUserId().equals(StrPool.ADMIN_ID)){
            return menuHelperMapper.selectList(Wraps.<SysResource>lbQ().eq(SysResource::getResourceType, 30));
        }
        List<SysResource> visibleResource = new ArrayList<>();
        List<Long> list = cacheOps.get(userResourceKey, k -> {
            visibleResource.addAll(menuHelperMapper.findVisibleAuth(resource));
            return visibleResource.stream().map(SysResource::getId).collect(Collectors.toList());
        });

        if (visibleResource.isEmpty() && CollUtil.isNotEmpty(list)) {
            visibleResource.addAll(menuHelperMapper.selectBatchIds(list));
        }
        return resourceListFilterGroup(resource.getMenuId(), visibleResource);
    }

    private List<SysResource> resourceListFilterGroup(Long menuId, List<SysResource> visibleResource) {
        if (menuId == null) {
            return visibleResource;
        }
        return visibleResource.stream().filter(item -> Objects.equals(menuId, item.getId())).collect(Collectors.toList());
    }
}
