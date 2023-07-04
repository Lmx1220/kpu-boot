package cn.lmx.kpu.authority.dao.auth;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
