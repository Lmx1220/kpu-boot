package cn.lmx.kpu.sms.strategy.impl;


import cn.lmx.kpu.sms.mapper.ESmsTaskMapper;
import cn.lmx.kpu.sms.service.ESmsSendStatusService;
import cn.lmx.kpu.sms.entity.ESmsSendStatus;
import cn.lmx.kpu.sms.entity.ESmsTask;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.enumeration.TaskStatus;
import cn.lmx.kpu.sms.strategy.domain.MsgParam;
import cn.lmx.kpu.sms.strategy.domain.SmsDO;
import cn.lmx.kpu.sms.strategy.domain.SmsResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.R;
import cn.lmx.kpu.sms.strategy.SmsStrategy;

import java.util.List;

/**
 * 抽象短信策略
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Slf4j

@RequiredArgsConstructor
public abstract class AbstractSmsStrategy implements SmsStrategy {

    private final ESmsTaskMapper smsTaskMapper;
    private final ESmsSendStatusService smsSendStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> sendSms(ESmsTask task, ESmsTemplate template) {
        String appId = template.getAppId();
        String appSecret = template.getAppSecret();
        String endPoint = template.getUrl();

        // 发送使用签名的调用ID
        String signName = template.getSignName();
        //参数json
        String templateParam = task.getTemplateParams();
        String templateCode = template.getTemplateCode();

        log.info("appId={}, appSecret={}, endPoint={},signName={}, templateCode={}", appId, appSecret, endPoint, signName, templateCode);
        log.info("templateParam={}", templateParam);

        try {
            List<ESmsSendStatus> list = smsSendStatusService.listByTaskId(task.getId());

            list.stream().forEach(smsSendStatus -> {
                //发送
                SmsResult result = send(SmsDO.builder()
                        .taskId(task.getId()).telNum(smsSendStatus.getTelNum()).appId(appId).appSecret(appSecret)
                        .signName(signName).templateCode(templateCode).endPoint(endPoint)
                        .templateParams(templateParam)
                        .templateContent(template.getContent())
                        .smsContent(task.getContent())
                        .build());

                log.info("phone={}, result={}", smsSendStatus.getTelNum(), result);
                smsSendStatus.setSendStatus(result.getSendStatus())
                        .setBizId(result.getBizId()).setExt(result.getExt())
                        .setCode(result.getCode()).setMessage(result.getMessage())
                        .setFee(result.getFee());
            });

            if (!list.isEmpty()) {
                smsSendStatusService.getSuperManager().updateBatchById(list);
            }
        } catch (Exception e) {
            log.warn("短信发送任务发送失败", e);
            updateStatus(task.getId(), TaskStatus.FAIL);
            return R.success(String.valueOf(task.getId()));
        }

        updateStatus(task.getId(), TaskStatus.SUCCESS);
        return R.success(String.valueOf(task.getId()));
    }

    public void updateStatus(Long taskId, TaskStatus success) {
        ESmsTask updateTask = new ESmsTask();
        updateTask.setId(taskId);
        updateTask.setStatus(success);
        smsTaskMapper.updateById(updateTask);
    }

    /**
     * 子类执行具体的发送任务
     *
     * @param smsDO 短信参数
     * @return 短信返回值
     */
    protected abstract SmsResult send(SmsDO smsDO);
}
