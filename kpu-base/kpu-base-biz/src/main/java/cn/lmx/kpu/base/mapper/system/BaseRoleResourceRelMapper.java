package cn.lmx.kpu.base.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.base.entity.system.BaseRoleResourceRel;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface BaseRoleResourceRelMapper extends SuperMapper<BaseRoleResourceRel> {

    /**
     * 根据角色id和角色类别，查询角色拥有的权限
     *
     * @param roleId   角色ID
     * @param category 角色类别
     * @return
     */
    List<BaseRoleResourceRel> findByRoleIdAndCategory(@Param("roleId") Long roleId, @Param("category") String category);


    /**
     * 根据应用id和角色id查询对应的资源ID
     *
     * @param applicationId applicationId
     * @param roleId        roleId
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> selectResourceIdByRoleId(@Param("applicationId") Long applicationId, @Param("roleId") Long roleId);

    /**
     * 查询员工在某应用下拥有的资源
     *
     * @param applicationId 应用ID
     * @param employeeId    员工ID
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> selectResourceIdByEmployeeId(@Param("applicationId") Long applicationId, @Param("employeeId") Long employeeId);
}
