package cn.lmx.kpu.authority.manager.auth.impl;

import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.authority.dao.auth.RoleOrgMapper;
import cn.lmx.kpu.authority.entity.auth.RoleOrg;
import cn.lmx.kpu.authority.manager.auth.RoleOrgManager;
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
public class RoleOrgManagerImpl extends SuperManagerImpl<RoleOrgMapper, RoleOrg> implements RoleOrgManager {


}
