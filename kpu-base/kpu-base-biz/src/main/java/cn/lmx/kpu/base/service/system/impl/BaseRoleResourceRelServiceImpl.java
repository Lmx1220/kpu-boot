package cn.lmx.kpu.base.service.system.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.base.entity.system.BaseRoleResourceRel;
import cn.lmx.kpu.base.manager.system.BaseRoleResourceRelManager;
import cn.lmx.kpu.base.service.system.BaseRoleResourceRelService;


/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseRoleResourceRelServiceImpl extends SuperServiceImpl<BaseRoleResourceRelManager, Long, BaseRoleResourceRel>
        implements BaseRoleResourceRelService {
}
