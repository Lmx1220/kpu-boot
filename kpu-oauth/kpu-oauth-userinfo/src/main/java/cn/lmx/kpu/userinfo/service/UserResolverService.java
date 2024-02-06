package cn.lmx.kpu.userinfo.service;

import cn.lmx.basic.base.R;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.model.vo.result.UserQuery;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
public interface UserResolverService {
    /**
     * 根据id查询用户
     *
     * @param userQuery 查询条件
     * @return 用户信息
     */
    R<SysUser> getById(UserQuery userQuery);
}
