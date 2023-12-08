package cn.lmx.kpu.sms.strategy;


import cn.lmx.kpu.sms.entity.ESmsTask;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.mapper.ESmsTaskMapper;
import cn.lmx.kpu.sms.mapper.ESmsTemplateMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.utils.ArgumentAssert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信发送上下文
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Component

public class SmsContext {
    private final Map<String, SmsStrategy> smsContextStrategyMap = new ConcurrentHashMap<>();

    private final ESmsTaskMapper smsTaskMapper;
    private final ESmsTemplateMapper smsTemplateMapper;

    public SmsContext(
            Map<String, SmsStrategy> strategyMap,
            ESmsTaskMapper smsTaskMapper,
            ESmsTemplateMapper smsTemplateMapper) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
        this.smsTaskMapper = smsTaskMapper;
        this.smsTemplateMapper = smsTemplateMapper;
    }

    /**
     * 根据任务id发送短信
     * <p>
     * 待完善的点：
     * 1， 查询次数过多，想办法优化
     *
     * @param taskId 任务id
     * @return 任务id
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void smsSend(Long taskId) {
        ESmsTask smsTask = smsTaskMapper.selectById(taskId);
        ArgumentAssert.notNull(smsTask, "短信任务尚未保存成功");

        ESmsTemplate template = smsTemplateMapper.selectById(smsTask.getTemplateId());
        ArgumentAssert.notNull(template, "短信模板为空");

        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
        SmsStrategy smsStrategy = smsContextStrategyMap.get(template.getProviderType().name());
        ArgumentAssert.notNull(smsStrategy, "短信供应商不存在");

        smsStrategy.sendSms(smsTask, template);
    }

}
