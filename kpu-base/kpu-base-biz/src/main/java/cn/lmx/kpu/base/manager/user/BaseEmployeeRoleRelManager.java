package cn.lmx.kpu.base.manager.user;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.base.entity.user.BaseEmployeeRoleRel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 员工的角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseEmployeeRoleRelManager extends SuperManager<BaseEmployeeRoleRel> {
    /**
     * 根据员工id删除 员工的角色
     *
     * @param employeeIds 员工
     * @return
     */
    boolean removeByEmployeeIds(Collection<Long> employeeIds);

    /**
     * 根据员工id删除 员工的角色
     *
     * @param employeeId 员工
     * @return
     */
    default boolean removeByEmployeeId(Long employeeId) {
        return removeByEmployeeIds(Collections.singletonList(employeeId));
    }

    /**
     * 给员工绑定指定的角色
     *
     * @param employeeIdList 员工
     * @param code           角色编码
     * @return
     */
    boolean bindRole(List<Long> employeeIdList, String code);

    /**
     * 解绑指定角色
     *
     * @param employeeIdList
     * @param code
     * @return
     */
    boolean unBindRole(List<Long> employeeIdList, String code);

    /**
     * 根据角色id 删除员工有用的角色
     *
     * @param idList idList
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    void deleteByRole(Collection<Long> idList);
}
