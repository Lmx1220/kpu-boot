package cn.lmx.kpu.userinfo.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.model.entity.base.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Repository
public interface RoleHelperMapper extends SuperMapper<SysRole> {

    /**
     * 根据员工-角色，查询员工拥有的角色编码
     *
     * @param userId 员工ID
     * @return
     */
    List<String> selectRoleCodeFromRoleByUserId(@Param("userId") Long userId);


    /**
     * 查询员工是否拥有指定角色编码
     *
     * @param codes
     * @param userId
     * @return
     */
    long countRoleFormRole(@Param("codes") List<String> codes, @Param("userId") Long userId);

    /**
     * 根据员工-角色，查询可用的资源ID
     * <p>
     * 部角色被禁用后，员工不在拥有此角色的权限
     *
     * @param userId 员工ID
     * @return
     */
    List<Long> selectResourceIdFromRoleByUserId(@Param("userId") Long userId);
}
