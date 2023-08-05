package cn.lmx.kpu.authority.manager.common.impl;


import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.model.log.OptLogDTO;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.authority.dao.common.OptLogExtMapper;
import cn.lmx.kpu.authority.dao.common.OptLogMapper;
import cn.lmx.kpu.authority.dto.common.OptLogResult;
import cn.lmx.kpu.authority.entity.common.OptLog;
import cn.lmx.kpu.authority.entity.common.OptLogExt;
import cn.lmx.kpu.authority.manager.common.OptLogManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
public class OptLogManagerImpl extends SuperManagerImpl<OptLogMapper, OptLog> implements OptLogManager {
    private final OptLogExtMapper optLogExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(OptLogDTO entity) {
        OptLog optLog = BeanPlusUtil.toBean(entity, OptLog.class);
        OptLogExt optLogExt = BeanPlusUtil.toBean(entity, OptLogExt.class);
        boolean bool = super.save(optLog);
        optLogExt.setId(optLog.getId());
        optLogExtMapper.insert(optLogExt);
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public OptLogResult getOptLogResultById(Long id) {
        OptLog opt = getById(id);
        OptLogResult result = BeanPlusUtil.toBean(opt, OptLogResult.class);
        OptLogExt optLogExt = optLogExtMapper.selectById(id);
        BeanPlusUtil.copyProperties(optLogExt, result);
        return result;
    }

}
