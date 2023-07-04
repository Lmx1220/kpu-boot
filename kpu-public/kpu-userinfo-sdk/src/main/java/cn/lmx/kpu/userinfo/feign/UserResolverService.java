package cn.lmx.kpu.userinfo.feign;

import cn.lmx.basic.base.R;
import cn.lmx.kpu.model.entity.base.SysUser;

/**
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface UserResolverService {
    /**
     * 根据id查询用户
     *¬
     * @param userQuery 查询条件
     * @return 用户信息
     */
    R<SysUser> getById(UserQuery userQuery);
}
