package cn.lmx.kpu.userinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.lmx.basic.base.R;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.model.vo.result.UserQuery;
import cn.lmx.kpu.userinfo.biz.EmployeeHelperBiz;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Component
public class UserResolverServiceImpl implements UserResolverService {
    @Autowired
    private EmployeeHelperBiz employeeBiz;

    @Override
    public R<SysUser> getById(UserQuery userQuery) {
        return R.success(employeeBiz.getSysUserById(userQuery));
    }
}
