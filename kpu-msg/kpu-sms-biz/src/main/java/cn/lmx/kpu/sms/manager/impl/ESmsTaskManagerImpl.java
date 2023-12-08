package cn.lmx.kpu.sms.manager.impl;

import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.sms.entity.ESmsTask;
import cn.lmx.kpu.sms.manager.ESmsTaskManager;
import cn.lmx.kpu.sms.mapper.ESmsTaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 * @create [2023-11-14 11:08:01] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ESmsTaskManagerImpl extends SuperManagerImpl<ESmsTaskMapper, ESmsTask> implements ESmsTaskManager {

}


