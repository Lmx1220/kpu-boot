package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.authority.dto.auth.MenuResourceTreeVO;
import cn.lmx.kpu.authority.entity.auth.Menu;

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
public interface MenuService extends SuperCacheService<Menu> {

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
    boolean updateWithCache(Menu menu);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 是否成功
     */
    boolean saveWithCache(Menu menu);

    /**
     * 查询用户可用菜单
     *
     * @param group  组
     * @param userId 用户id
     * @return 菜单
     */
    List<Menu> findVisibleMenu(String group, Long userId);

    /**
     * 查询系统所有的菜单和资源树
     *
     * @return java.util.List<cn.lmx.kpu.authority.dto.auth.MenuResourceTreeVO>
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    List<MenuResourceTreeVO> findMenuResourceTree();

    /**
     * 查询系统所有的数据权限
     *
     * @return java.util.List<cn.lmx.kpu.authority.dto.auth.MenuResourceTreeVO>
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    List<MenuResourceTreeVO> findMenuDataScopeTree();
}
