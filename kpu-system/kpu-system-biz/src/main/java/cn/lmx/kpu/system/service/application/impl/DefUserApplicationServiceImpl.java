package cn.lmx.kpu.system.service.application.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;

import cn.lmx.kpu.system.entity.application.DefUserApplication;
import cn.lmx.kpu.system.manager.application.DefUserApplicationManager;
import cn.lmx.kpu.system.service.application.DefUserApplicationService;

/**
 * <p>
 * 业务实现类
 * 用户的默认应用
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefUserApplicationServiceImpl extends SuperServiceImpl<DefUserApplicationManager, Long, DefUserApplication> implements DefUserApplicationService {
    @Override
    public Long getMyDefAppByUserId(Long userId) {
        DefUserApplication userApplication = superManager.getOne(Wraps.<DefUserApplication>lbQ().eq(DefUserApplication::getUserId, userId), false);
        return userApplication != null ? userApplication.getApplicationId() : null;
    }
}
