package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLog;
import cn.lmx.kpu.msg.manager.ExtendInterfaceLogManager;
import cn.lmx.kpu.msg.mapper.ExtendInterfaceLogMapper;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendInterfaceLogManagerImpl extends SuperManagerImpl<ExtendInterfaceLogMapper, ExtendInterfaceLog> implements ExtendInterfaceLogManager {
    @Override
    public ExtendInterfaceLog getByInterfaceId(Long interfaceId) {
        return getOne(Wraps.<ExtendInterfaceLog>lbQ().eq(ExtendInterfaceLog::getInterfaceId, interfaceId));
    }

    @Override
    public void incrSuccessCount(Long id) {
        baseMapper.incrSuccessCount(id, LocalDateTime.now());
    }

    @Override
    public void incrFailCount(Long id) {
        baseMapper.incrFailCount(id, LocalDateTime.now());
    }
}


