package cn.lmx.kpu.base.service.user.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.base.entity.user.BaseEmployeeRoleRel;
import cn.lmx.kpu.base.manager.user.BaseEmployeeRoleRelManager;
import cn.lmx.kpu.base.service.user.BaseEmployeeRoleRelService;


import java.util.List;

/**
 * <p>
 * 业务实现类
 * 员工的角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeRoleRelServiceImpl extends SuperServiceImpl<BaseEmployeeRoleRelManager, Long, BaseEmployeeRoleRel> implements BaseEmployeeRoleRelService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindRole(List<Long> employeeIdList, String code) {
        return superManager.bindRole(employeeIdList, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unBindRole(List<Long> employeeIdList, String code) {
        return superManager.unBindRole(employeeIdList, code);
    }
}
