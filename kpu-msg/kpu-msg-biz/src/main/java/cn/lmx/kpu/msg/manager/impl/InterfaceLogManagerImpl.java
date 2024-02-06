package cn.lmx.kpu.msg.manager.impl;

import cn.lmx.basic.database.mybatis.conditions.Wraps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.InterfaceLog;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.InterfaceLogManager;
import cn.lmx.kpu.msg.mapper.InterfaceLogMapper;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class InterfaceLogManagerImpl extends SuperManagerImpl<InterfaceLogMapper, InterfaceLog> implements InterfaceLogManager {
    @Override
    public InterfaceLog getByInterfaceId(Long interfaceId) {
        return getOne(Wraps.<InterfaceLog>lbQ().eq(InterfaceLog::getInterfaceId, interfaceId));
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


