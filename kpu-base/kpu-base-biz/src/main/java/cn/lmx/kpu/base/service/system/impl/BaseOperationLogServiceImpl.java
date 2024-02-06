package cn.lmx.kpu.base.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.base.entity.system.BaseOperationLog;
import cn.lmx.kpu.base.entity.system.BaseOperationLogExt;
import cn.lmx.kpu.base.manager.system.BaseOperationLogManager;
import cn.lmx.kpu.base.mapper.system.BaseOperationLogExtMapper;
import cn.lmx.kpu.base.service.system.BaseOperationLogService;
import cn.lmx.kpu.base.vo.result.system.BaseOperationLogResultVO;
import cn.lmx.kpu.base.vo.save.system.BaseOperationLogSaveVO;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务实现类
 * 操作日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseOperationLogServiceImpl extends SuperServiceImpl<BaseOperationLogManager, Long, BaseOperationLog> implements BaseOperationLogService {

    private final BaseOperationLogExtMapper baseOperationLogExtMapper;

    @Override
    public BaseOperationLogResultVO getDetail(Long id) {
        BaseOperationLog operationLog = superManager.getById(id);
        BaseOperationLogExt ext = baseOperationLogExtMapper.selectById(id);

        BaseOperationLogResultVO result = BeanUtil.toBean(ext, BaseOperationLogResultVO.class);
        BeanUtil.copyProperties(operationLog, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return superManager.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }

    @Override
    public <SaveVO> BaseOperationLog save(SaveVO saveVO) {
        BaseOperationLogSaveVO logSaveVO = (BaseOperationLogSaveVO) saveVO;
        BaseOperationLogExt baseOperationLogExt = BeanUtil.toBean(saveVO, BaseOperationLogExt.class);
        baseOperationLogExtMapper.insert(baseOperationLogExt);
        logSaveVO.setId(baseOperationLogExt.getId());
        return super.save(logSaveVO);
    }
}
