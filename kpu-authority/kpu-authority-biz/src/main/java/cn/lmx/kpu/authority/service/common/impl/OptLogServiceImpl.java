package cn.lmx.kpu.authority.service.common.impl;


import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.model.log.OptLogDTO;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.authority.dao.common.OptLogExtMapper;
import cn.lmx.kpu.authority.dto.common.OptLogResult;
import cn.lmx.kpu.authority.entity.common.OptLog;
import cn.lmx.kpu.authority.entity.common.OptLogExt;
import cn.lmx.kpu.authority.manager.common.OptLogManager;
import cn.lmx.kpu.authority.service.common.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OptLogServiceImpl extends SuperServiceImpl<OptLogManager, Long, OptLog, OptLog, OptLog, OptLog, OptLogResult> implements OptLogService {
    private final OptLogExtMapper optLogExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(OptLogDTO entity) {
        return superManager.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return superManager.clearLog(clearBeforeTime, clearBeforeNum);
    }

    @Override
    @Transactional(readOnly = true)
    public OptLogResult getOptLogResultById(Long id) {
        return superManager.getOptLogResultById(id);
    }

}
