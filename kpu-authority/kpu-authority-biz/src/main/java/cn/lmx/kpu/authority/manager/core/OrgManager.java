package cn.lmx.kpu.authority.manager.core;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.core.Org;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 账号
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface OrgManager extends SuperCacheManager<Org> {
    /**
     * 查询指定id集合下的所有子集
     *
     * @param ids id
     * @return 所有的子组织
     */
    List<Org> findChildren(List<Long> ids);

    /**
     * 批量删除以及删除其子节点
     *
     * @param ids id
     * @return 是否成功
     */
    boolean remove(List<Long> ids);

    /**
     * 检测名称是否存在
     *
     * @param id   id
     * @param name name
     * @return boolean
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    boolean check(Long id, String name);


    /**
     * 查询员工主部门ID
     *
     * @param userId 员工ID
     * @return
     */
    Long getMainDeptIdByUserId(Long userId);

    /**
     * 查询员工主部门及其所有的子部门ID
     *
     * @param userId 员工ID
     * @return
     */
    List<Long> findDeptAndChildrenIdByUserId(Long userId);

    /**
     * 查询员工主部门
     *
     * @param userId 员工ID
     * @return
     */
    Org getMainCompanyByUserId(Long userId);

    /**
     * 查询员工主部门的所属单位 ID
     *
     * @param userId 员工ID
     * @return
     */
    Long getMainCompanyIdByUserId(Long userId);

    /**
     * 查询员工主部门所属单位以及单位的所有子组织 ID
     *
     * @param userId 员工ID
     * @return
     */
    List<Long> findCompanyAndChildrenIdByUserId(Long userId);

}
