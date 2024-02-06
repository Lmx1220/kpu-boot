package cn.lmx.kpu.datascope.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.datascope.entity.BaseOrgBO;
import cn.lmx.kpu.datascope.entity.DefResourceDataScope;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface DataScopeMapper extends SuperMapper<BaseOrgBO> {

    /**
     * 查询指定应用、指定path的数据权限
     *
     * @param applicationId   应用ID
     * @param path            路径
     * @param dataScopeIdList 数据权限ID
     * @return
     */
    @InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
    List<DefResourceDataScope> findDataScopeByPath(@Param("applicationId") Long applicationId, @Param("path") String path, @Param("dataScopeIdList") List<Long> dataScopeIdList);

    /**
     * 查询指定应用、指定path的默认数据权限
     *
     * @param applicationId 应用ID
     * @param path          路径
     * @return
     */
    @InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
    List<DefResourceDataScope> findDefDataScopeByPath(@Param("applicationId") Long applicationId, @Param("path") String path);

    /**
     * 根据员工-角色-数据权限关系，查询可用的数据权限ID
     * <p>
     * 角色被禁用后，员工不在拥有此角色的权限
     *
     * @param employeeId 员工ID
     * @param category   角色类别
     * @return
     */
    List<Long> selectDataScopeIdFromRoleByEmployeeId(@Param("employeeId") Long employeeId, @Param("category") String category);

    /**
     * 根据员工-部门-角色-数据权限关系，查询可用的数据权限ID
     * <p>
     * 角色被禁用后，员工不在拥有此角色的权限
     *
     * @param employeeId 员工ID
     * @param category   角色类别
     * @return
     */
    List<Long> selectDataScopeIdFromOrgByEmployeeId(@Param("employeeId") Long employeeId, @Param("category") String category);


    /**
     * 根据员工ID查找主部门
     *
     * @param employeeId 员工ID
     * @return
     */
    BaseOrgBO getMainDeptIdByEmployeeId(@Param("employeeId") Long employeeId);
}
