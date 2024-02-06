package cn.lmx.kpu.base.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.base.entity.system.BaseRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface BaseRoleMapper extends SuperMapper<BaseRole> {

    /**
     * 根据角色id查询员工id
     *
     * @param roleIds roleIds
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 上午
     * @create [2024/02/07  02:04 上午 ] [lmx] [初始创建]
     */
    List<Long> listEmployeeIdByRoleId(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据机构id查询对应的角色
     *
     * @param orgId orgId
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> selectRoleIdByOrgId(@Param("orgId") Long orgId);

    /**
     * 查询员工拥有的角色ID
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> selectRoleByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 查询员工拥有的角色
     *
     * @param employeeId 员工id
     * @param codes      角色编码
     * @return java.util.List<BaseRole> 角色
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<BaseRole> selectRoleByEmployee(@Param("employeeId") Long employeeId, @Param("codes") String... codes);

}
