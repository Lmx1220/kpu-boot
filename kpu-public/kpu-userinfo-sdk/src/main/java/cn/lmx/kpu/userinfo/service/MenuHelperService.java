package cn.lmx.kpu.userinfo.service;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.kpu.common.cache.auth.MenuCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserResourceCacheKeyBuilder;
import cn.lmx.kpu.model.entity.base.SysMenu;
import cn.lmx.kpu.model.vo.query.MenuQueryDTO;
import cn.lmx.kpu.userinfo.dao.MenuHelperMapper;
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
public class MenuHelperService {
    private final MenuHelperMapper menuHelperMapper;
    private final CacheOps cacheOps;

    protected void setCache(SysMenu model) {
        Object id = model.getId();
        if (id != null) {
            CacheKey key = new MenuCacheKeyBuilder().key(id);
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
    public List<SysMenu> findVisibleResource(MenuQueryDTO resource) {
        ContextUtil.setDatabaseBase();
        CacheKey userMenuKey = new UserResourceCacheKeyBuilder().key(resource.getUserId());

        List<SysMenu> visibleMenu = new ArrayList<>();
        List<Long> list = cacheOps.get(userMenuKey, k -> {
            visibleMenu.addAll(menuHelperMapper.findVisibleResource(resource));
            return visibleMenu.stream().map(SysMenu::getId).collect(Collectors.toList());
        });

        if (visibleMenu.isEmpty() && CollUtil.isNotEmpty(list)) {
            visibleMenu.addAll(menuHelperMapper.selectBatchIds(list));
        }
        return resourceListFilterGroup(resource.getMenuId(), visibleMenu);
    }

    private List<SysMenu> resourceListFilterGroup(Long menuId, List<SysMenu> visibleMenu) {
        if (menuId == null) {
            return visibleMenu;
        }
        return visibleMenu.stream().filter(item -> Objects.equals(menuId, item.getId())).collect(Collectors.toList());
    }
}
