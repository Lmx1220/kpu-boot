package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.InterfaceLogService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.InterfaceLogManager;
import cn.lmx.kpu.msg.entity.InterfaceLog;
import cn.lmx.kpu.msg.vo.save.InterfaceLogSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLogUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLogResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLogPageQuery;

/**
 * <p>
 * 业务实现类
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
@Transactional(readOnly = true)
public class InterfaceLogServiceImpl extends SuperServiceImpl<InterfaceLogManager, Long, InterfaceLog, InterfaceLogSaveVO,
    InterfaceLogUpdateVO, InterfaceLogPageQuery, InterfaceLogResultVO> implements InterfaceLogService {


}


