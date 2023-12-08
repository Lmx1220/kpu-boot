package cn.lmx.kpu.sms.manager.impl;

import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.manager.ESmsTemplateManager;
import cn.lmx.kpu.sms.mapper.ESmsTemplateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 短信模板
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ESmsTemplateManagerImpl extends SuperManagerImpl<ESmsTemplateMapper, ESmsTemplate> implements ESmsTemplateManager {

}


