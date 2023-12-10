package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.InterfaceLoggingService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.InterfaceLoggingManager;
import cn.lmx.kpu.msg.entity.InterfaceLogging;
import cn.lmx.kpu.msg.vo.save.InterfaceLoggingSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLoggingUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLoggingResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLoggingPageQuery;

/**
 * <p>
 * 业务实现类
 * 接口执行日志记录
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class InterfaceLoggingServiceImpl extends SuperServiceImpl<InterfaceLoggingManager, Long, InterfaceLogging, InterfaceLoggingSaveVO,
    InterfaceLoggingUpdateVO, InterfaceLoggingPageQuery, InterfaceLoggingResultVO> implements InterfaceLoggingService {


}


