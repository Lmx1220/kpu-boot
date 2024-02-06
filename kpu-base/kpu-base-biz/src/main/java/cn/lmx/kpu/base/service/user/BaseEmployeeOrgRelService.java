package cn.lmx.kpu.base.service.user;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.base.entity.user.BaseEmployeeOrgRel;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 员工所在部门
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseEmployeeOrgRelService extends SuperService<Long, BaseEmployeeOrgRel> {
    /**
     * 根据员工id查询员工的机构id
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findOrgIdListByEmployeeId(Long employeeId);
}
