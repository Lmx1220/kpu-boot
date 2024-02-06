package cn.lmx.kpu.userinfo.biz;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import cn.lmx.kpu.base.entity.user.BaseEmployee;
import cn.lmx.kpu.base.entity.user.BaseOrg;
import cn.lmx.kpu.base.entity.user.BasePosition;
import cn.lmx.kpu.base.service.system.BaseRoleService;
import cn.lmx.kpu.base.service.user.BaseEmployeeService;
import cn.lmx.kpu.base.service.user.BaseOrgService;
import cn.lmx.kpu.base.service.user.BasePositionService;
import cn.lmx.kpu.model.entity.base.SysEmployee;
import cn.lmx.kpu.model.entity.base.SysOrg;
import cn.lmx.kpu.model.entity.base.SysPosition;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.model.vo.result.UserQuery;
import cn.lmx.kpu.oauth.biz.ResourceBiz;
import cn.lmx.kpu.system.entity.tenant.DefUser;
import cn.lmx.kpu.system.service.tenant.DefUserService;

import java.util.List;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class EmployeeHelperBiz {
    private final DefUserService defUserService;
    private final BaseEmployeeService baseEmployeeService;
    private final BaseRoleService baseRoleService;
    private final BaseOrgService baseOrgService;
    private final BasePositionService basePositionService;
    private final ResourceBiz resourceBiz;

    private boolean notEmpty(Long val) {
        return val != null && !Long.valueOf(0).equals(val);
    }

    public SysUser getSysUserById(UserQuery query) {
        Long userId = query.getUserId();
        DefUser defUser = defUserService.getByIdCache(userId);
        if (defUser == null) {
            return new SysUser();
        }
        SysUser sysUser = BeanUtil.toBean(defUser, SysUser.class);
        boolean notEmptyEmployee = notEmpty(query.getEmployeeId());
        boolean queryEmployee = query.getFull() || query.getEmployee();
        boolean queryOrg = query.getFull() || query.getOrg();
        boolean queryCurrentOrg = query.getFull() || query.getCurrentOrg();
        boolean queryPosition = query.getFull() || query.getPosition();
        boolean queryResource = query.getFull() || query.getResource();
        boolean queryRoles = query.getFull() || query.getRoles();
        boolean anyQuery = queryEmployee || queryOrg || queryCurrentOrg || queryPosition || queryResource || queryRoles;
        if (notEmptyEmployee && anyQuery) {
            BaseEmployee baseEmployee = baseEmployeeService.getByIdCache(query.getEmployeeId());
            if (baseEmployee == null) {
                return sysUser;
            }
            sysUser.setEmployee(BeanUtil.toBean(baseEmployee, SysEmployee.class));
            // 当前单位
            if (queryCurrentOrg && notEmpty(baseEmployee.getLastCompanyId())) {
                BaseOrg baseOrg = baseOrgService.getByIdCache(baseEmployee.getLastCompanyId());
                sysUser.setCompany(BeanUtil.toBean(baseOrg, SysOrg.class));
            }
            // 当前部门
            if (queryCurrentOrg && notEmpty(baseEmployee.getLastDeptId())) {
                BaseOrg baseOrg = baseOrgService.getByIdCache(baseEmployee.getLastDeptId());
                sysUser.setDept(BeanUtil.toBean(baseOrg, SysOrg.class));
            }
            // 他所在的 单位和部门
            if (queryOrg) {
                List<BaseOrg> companyList = baseOrgService.findCompanyByEmployeeId(baseEmployee.getId());
                sysUser.setCompanyList(BeanUtil.copyToList(companyList, SysOrg.class));

                List<BaseOrg> deptList = baseOrgService.findDeptByEmployeeId(baseEmployee.getId(), baseEmployee.getLastCompanyId());
                sysUser.setDeptList(BeanUtil.copyToList(deptList, SysOrg.class));
            }
            // 岗位
            if (queryPosition && notEmpty(baseEmployee.getPositionId())) {
                BasePosition basePosition = basePositionService.getById(baseEmployee.getPositionId());
                sysUser.setPosition(BeanUtil.toBean(basePosition, SysPosition.class));
            }
            // 资源
            if (queryResource) {
                List<String> resources = resourceBiz.findVisibleResource(baseEmployee.getId(), null);
                sysUser.setResourceCodeList(resources);
            }
            // 角色
            if (queryRoles) {
                List<String> codes = baseRoleService.findRoleCodeByEmployeeId(baseEmployee.getId());
                sysUser.setRoleCodeList(codes);
            }
        }
        return sysUser;
    }
}
