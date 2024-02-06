package cn.lmx.kpu.base.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.base.entity.user.BaseEmployeeOrgRel;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 员工所在部门
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface BaseEmployeeOrgRelMapper extends SuperMapper<BaseEmployeeOrgRel> {

    /**
     * 查询员工拥有的机构
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> selectOrgByEmployeeId(@Param("employeeId") Long employeeId);
}
