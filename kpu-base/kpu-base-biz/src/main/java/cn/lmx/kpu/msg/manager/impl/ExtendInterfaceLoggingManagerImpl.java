package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLogging;
import cn.lmx.kpu.msg.manager.ExtendInterfaceLoggingManager;
import cn.lmx.kpu.msg.mapper.ExtendInterfaceLoggingMapper;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志记录
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendInterfaceLoggingManagerImpl extends SuperManagerImpl<ExtendInterfaceLoggingMapper, ExtendInterfaceLogging> implements ExtendInterfaceLoggingManager {

}


