package cn.lmx.kpu.base.manager.system;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.base.entity.system.BaseRole;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseRoleManager extends SuperCacheManager<BaseRole> {
    /**
     * 查询员工的所有角色ID
     *
     * @param employeeId employeeId
     * @return java.util.List<cn.lmx.kpu.base.entity.system.BaseRole>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> findRoleIdByEmployeeId(Long employeeId);

    /**
     * 查询员工的所有角色
     *
     * @param employeeId employeeId
     * @return java.util.List<cn.lmx.kpu.base.entity.system.BaseRole>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<BaseRole> findRoleByEmployeeId(Long employeeId);

    /**
     * 根据角色编码查询角色
     *
     * @param code
     * @return
     */
    BaseRole getRoleByCode(String code);


    /**
     * 根据角色id查询员工id
     *
     * @param roleIds roleIds
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 上午
     * @create [2024/02/07  02:04 上午 ] [lmx] [初始创建]
     */
    List<Long> listEmployeeIdByRoleId(List<Long> roleIds);

    /**
     * 查询员工拥有的资源
     *
     * @param employeeId    员工ID
     * @param applicationId 应用ID
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> findResourceIdByEmployeeId(Long applicationId, Long employeeId);

    /**
     * 检查某个员工{employeeId}是否拥有任意一个角色{codes}
     *
     * @param employeeId employeeId
     * @param codes      codes
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    boolean checkRole(Long employeeId, String... codes);

}
