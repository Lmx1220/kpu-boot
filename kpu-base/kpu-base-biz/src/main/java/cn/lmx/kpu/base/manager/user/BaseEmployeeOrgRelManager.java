package cn.lmx.kpu.base.manager.user;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.base.entity.user.BaseEmployeeOrgRel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 员工所在部门
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseEmployeeOrgRelManager extends SuperManager<BaseEmployeeOrgRel> {
    /**
     * 查询员工所属的机构
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<Long> findOrgIdByEmployeeId(Long employeeId);

    /**
     * 根据员工id删除 员工的组织机构
     *
     * @param employeeIds 员工
     * @return
     */
    boolean removeByEmployeeIds(Collection<Long> employeeIds);

    /**
     * 根据员工id删除 员工的组织机构
     *
     * @param employeeId 员工
     * @return
     */
    default boolean removeByEmployeeId(Long employeeId) {
        return removeByEmployeeIds(Collections.singletonList(employeeId));
    }

    /**
     * 根据机构ID删除机构下的员工
     *
     * @param orgIdList 机构ID
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    void deleteByOrg(Collection<Long> orgIdList);
}
