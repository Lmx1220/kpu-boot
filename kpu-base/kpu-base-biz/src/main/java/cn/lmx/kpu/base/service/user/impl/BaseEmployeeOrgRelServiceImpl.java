package cn.lmx.kpu.base.service.user.impl;

import cn.hutool.core.convert.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.base.entity.user.BaseEmployeeOrgRel;
import cn.lmx.kpu.base.manager.user.BaseEmployeeOrgRelManager;
import cn.lmx.kpu.base.service.user.BaseEmployeeOrgRelService;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 员工所在部门
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeOrgRelServiceImpl extends SuperServiceImpl<BaseEmployeeOrgRelManager, Long, BaseEmployeeOrgRel> implements BaseEmployeeOrgRelService {
    @Override
    public List<Long> findOrgIdListByEmployeeId(Long employeeId) {
        ArgumentAssert.notNull(employeeId, "员工id不能为空");
        return superManager.listObjs(Wraps.<BaseEmployeeOrgRel>lbQ().select(BaseEmployeeOrgRel::getOrgId).eq(BaseEmployeeOrgRel::getEmployeeId, employeeId), Convert::toLong);
    }
}
