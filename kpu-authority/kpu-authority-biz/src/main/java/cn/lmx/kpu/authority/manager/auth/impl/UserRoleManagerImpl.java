package cn.lmx.kpu.authority.manager.auth.impl;

import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.authority.dao.auth.UserRoleMapper;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.UserRoleManager;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserRoleManagerImpl extends SuperManagerImpl<UserRoleMapper, UserRole> implements UserRoleManager {


}
