package cn.lmx.kpu.system.manager.application;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.system.entity.application.DefResourceApi;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务层
 * 资源API
 * </p>
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
public interface DefResourceApiManager extends SuperCacheManager<DefResourceApi> {
    /**
     * 根据资源id 删除资源的接口
     *
     * @param resourceIdList 资源id
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    void removeByResourceId(List<Long> resourceIdList);

    /**
     * 根据资源id查询资源接口
     *
     * @param resourceId 资源id
     * @return java.util.List<cn.lmx.kpu.tenant.vo.result.tenant.DefResourceApiResultVO>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<DefResourceApi> findByResourceId(Long resourceId);

    /**
     * 根据资源id查询资源 api id
     *
     * @param resourceIdList
     * @return
     */
    List<DefResourceApi> findApiByResourceId(List<Long> resourceIdList);

    /**
     * 查询指定租户下指定应用和指定资源类型的 接口
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<cn.lmx.kpu.system.entity.application.DefResourceApi>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<DefResourceApi> findResourceApi(List<Long> applicationIdList,
                                         Collection<String> resourceTypes);
}
