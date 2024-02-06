package cn.lmx.kpu.base.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.base.entity.user.BaseEmployee;
import cn.lmx.kpu.base.vo.query.user.BaseEmployeePageQuery;
import cn.lmx.kpu.base.vo.result.user.BaseEmployeeResultVO;
import cn.lmx.kpu.base.vo.save.user.BaseEmployeeRoleRelSaveVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务接口
 * 员工
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseEmployeeService extends SuperCacheService<Long, BaseEmployee> {
    /**
     * 批量保存
     *
     * @param entityList entityList
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    boolean saveBatch(Collection<BaseEmployee> entityList);

    /**
     * 给员工分配角色
     *
     * @param employeeRoleSaveVO
     * @return
     */
    List<Long> saveEmployeeRole(BaseEmployeeRoleRelSaveVO employeeRoleSaveVO);

    /**
     * 根据员工id查询员工的角色
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findEmployeeRoleByEmployeeId(Long employeeId);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    IPage<BaseEmployeeResultVO> findPageResultVO(PageParams<BaseEmployeePageQuery> params);


    /**
     * 批量保存 基础库员工和系统角色
     *
     * @param employeeList
     * @return
     */
    boolean saveBatchBaseEmployeeAndRole(List<BaseEmployee> employeeList);

    /**
     * 根据ID修改不为空的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    boolean updateById(BaseEmployee baseEmployee);

    /**
     * 根据ID修改所有的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    boolean updateAllById(BaseEmployee baseEmployee);

    /**
     * 查询指定企业指定用户的员工信息
     *
     * @param userId 用户id
     * @return
     */
    BaseEmployee getEmployeeByUser(Long userId);


    /**
     * 根据用户id查询员工
     *
     * @param userId 用户id
     * @return
     */
    List<BaseEmployeeResultVO> listEmployeeByUserId(Long userId);
}
