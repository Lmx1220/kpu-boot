package cn.lmx.kpu.oauth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lmx.basic.cache.redis2.CacheResult;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.base.entity.user.BaseEmployee;
import cn.lmx.kpu.base.entity.user.BaseOrg;
import cn.lmx.kpu.base.service.user.BaseEmployeeService;
import cn.lmx.kpu.base.service.user.BaseOrgService;
import cn.lmx.kpu.common.cache.common.CaptchaCacheKeyBuilder;
import cn.lmx.kpu.common.properties.SystemProperties;
import cn.lmx.kpu.oauth.service.UserInfoService;
import cn.lmx.kpu.oauth.vo.param.RegisterByEmailVO;
import cn.lmx.kpu.oauth.vo.param.RegisterByMobileVO;
import cn.lmx.kpu.oauth.vo.result.OrgResultVO;
import cn.lmx.kpu.system.entity.tenant.DefUser;
import cn.lmx.kpu.system.service.tenant.DefUserService;

import java.util.List;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    protected BaseEmployeeService baseEmployeeService;
    @Autowired
    protected BaseOrgService baseOrgService;
    @Autowired
    protected DefUserService defUserService;
    @Autowired
    protected CacheOps cacheOps;

    @Autowired
    protected SystemProperties systemProperties;

    @Override
    public OrgResultVO findCompanyAndDept() {
        Long userId = ContextUtil.getUserId();
        Long companyId = ContextUtil.getCurrentCompanyId();
        Long deptId = ContextUtil.getCurrentDeptId();
        BaseEmployee baseEmployee = baseEmployeeService.getEmployeeByUser(userId);
        ArgumentAssert.notNull(baseEmployee, "用户不属于该企业");

        // 上次登录的单位
        List<BaseOrg> companyList = baseOrgService.findCompanyByEmployeeId(baseEmployee.getId());
        Long currentCompanyId = companyId != null ? companyId : baseEmployee.getLastCompanyId();

        // 上次登录的部门
        List<BaseOrg> deptList = baseOrgService.findDeptByEmployeeId(baseEmployee.getId(), currentCompanyId);
        Long currentDeptId = deptId != null ? deptId : baseEmployee.getLastDeptId();
        return OrgResultVO.builder()
                .companyList(companyList).currentCompanyId(currentCompanyId)
                .deptList(deptList).currentDeptId(currentDeptId).build();
    }

    @Override
    public List<BaseOrg> findDeptByCompany(Long companyId) {
        Long employeeId = ContextUtil.getEmployeeId();
        return baseOrgService.findDeptByEmployeeId(employeeId, companyId);
    }

    @Override
    public String registerByMobile(RegisterByMobileVO register) {
        if (systemProperties.getVerifyCaptcha()) {
//            短信验证码
            CacheKey cacheKey = new CaptchaCacheKeyBuilder().key(register.getMobile(), register.getKey());
            CacheResult<String> code = cacheOps.get(cacheKey);
            ArgumentAssert.equals(code.getValue(), register.getCode(), "验证码不正确");
        }
        ArgumentAssert.equals(register.getConfirmPassword(), register.getPassword(), "密码和确认密码不一致");
        DefUser defUser = BeanUtil.toBean(register, DefUser.class);

        defUserService.register(defUser);

        return defUser.getMobile();
    }

    @Override
    public String registerByEmail(RegisterByEmailVO register) {
        if (systemProperties.getVerifyCaptcha()) {
//            短信验证码
            CacheKey cacheKey = new CaptchaCacheKeyBuilder().key(register.getEmail(), register.getKey());
            CacheResult<String> code = cacheOps.get(cacheKey);
            ArgumentAssert.equals(code.getValue(), register.getCode(), "验证码不正确");
        }
        ArgumentAssert.equals(register.getConfirmPassword(), register.getPassword(), "密码和确认密码不一致");
        DefUser defUser = BeanUtil.toBean(register, DefUser.class);

        defUserService.registerByEmail(defUser);

        return defUser.getEmail();
    }
}
