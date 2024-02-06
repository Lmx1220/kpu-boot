package cn.lmx.kpu.system.service.application;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.system.entity.application.DefApplication;
import cn.lmx.kpu.system.vo.result.application.ApplicationResourceResultVO;
import cn.lmx.kpu.system.vo.result.application.DefApplicationResultVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefApplicationService extends SuperCacheService<Long, DefApplication> {

    /**
     * id!=null：排除此id，检测name是否重复
     * id==null: 直接检测name是否重复
     *
     * @param id   应用id
     * @param name 应用名
     * @return java.lang.Boolean
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    Boolean check(Long id, String name);

    /**
     * 查询全部应用资源列表
     *
     * @return java.util.List<cn.lmx.kpu.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<ApplicationResourceResultVO> findApplicationResourceList();

    /**
     * 查询可用的应用资源列表
     *
     * @return java.util.List<cn.lmx.kpu.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<ApplicationResourceResultVO> findAvailableApplicationResourceList();

    /**
     * 查询我的应用
     *
     * @param name 应用名
     * @return
     */
    List<DefApplicationResultVO> findMyApplication(String name);

    /**
     * 查询推荐应用
     *
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findRecommendApplication(String name);

    /**
     * 查询可用的应用数据权限列表
     *
     * @return java.util.List<cn.lmx.kpu.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<ApplicationResourceResultVO> findAvailableApplicationDataScopeList();

    /**
     * 修改默认应用
     *
     * @param applicationId 应用Id
     * @param userId        用户ID
     * @return
     */
    Boolean updateDefApp(Long applicationId, Long userId);

    /**
     * 查询默认应用
     *
     * @param userId
     * @return
     */
    DefApplication getDefApp(Long userId);

}
