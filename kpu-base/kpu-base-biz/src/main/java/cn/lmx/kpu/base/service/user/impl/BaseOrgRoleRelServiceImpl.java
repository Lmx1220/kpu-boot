package cn.lmx.kpu.base.service.user.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.base.entity.user.BaseOrgRoleRel;
import cn.lmx.kpu.base.manager.user.BaseOrgRoleRelManager;
import cn.lmx.kpu.base.service.user.BaseOrgRoleRelService;


/**
 * <p>
 * 业务实现类
 * 组织的角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseOrgRoleRelServiceImpl extends SuperServiceImpl<BaseOrgRoleRelManager, Long, BaseOrgRoleRel> implements BaseOrgRoleRelService {

}
