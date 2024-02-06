package cn.lmx.kpu.msg.strategy;


import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.json.JSONUtil;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.msg.entity.*;
import cn.lmx.kpu.msg.enumeration.InterfaceExecModeEnum;
import cn.lmx.kpu.msg.enumeration.MsgInterfaceLoggingStatusEnum;
import cn.lmx.kpu.msg.enumeration.TaskStatus;
import cn.lmx.kpu.msg.glue.GlueFactory;
import cn.lmx.kpu.msg.manager.InterfaceLogManager;
import cn.lmx.kpu.msg.manager.InterfaceLoggingManager;
import cn.lmx.kpu.msg.manager.MsgManager;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.utils.ArgumentAssert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 短信发送上下文
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Component
@Slf4j

@RequiredArgsConstructor
public class MsgContext {
    private final InterfaceLogManager interfaceLogManager;
    private final MsgManager msgManager;
    private final InterfaceLoggingManager interfaceLoggingManager;

    @Transactional(rollbackFor = Exception.class)

    public boolean execSend(Msg extendMsg,
                            MsgTemplate extendMsgTemplate,
                            List<MsgRecipient> recipientList,
                            Interface defInterface,
                            Map<String, Object> propertyParams) {
        InterfaceLog extendInterfaceLog = interfaceLogManager.getByInterfaceId(defInterface.getId());
        if (extendInterfaceLog == null) {
            extendInterfaceLog = new InterfaceLog();
            extendInterfaceLog.setInterfaceId(defInterface.getId());
            extendInterfaceLog.setName(defInterface.getName());
            extendInterfaceLog.setFailCount(0);
            extendInterfaceLog.setSuccessCount(0);
            interfaceLogManager.save(extendInterfaceLog);
        }

        InterfaceLogging logging = InterfaceLogging.builder()
                .status(MsgInterfaceLoggingStatusEnum.INIT.getValue())
                .logId(extendInterfaceLog.getId())
                .bizId(extendMsg.getBizId())
                .execTime(LocalDateTime.now())
                .params(extendMsg.getParam())
                .build();


        MsgParam msgParam = MsgParam.builder().msg(extendMsg).msgTemplate(extendMsgTemplate)
                .propertyParams(propertyParams)
                .recipientList(recipientList).build();

        try {
            MsgResult result;
            MsgStrategy msgStrategy;
            if (InterfaceExecModeEnum.IMPL_CLASS.eq(defInterface.getExecMode())) {
                // 实现类
                String implClass = defInterface.getImplClass();
                msgStrategy = SpringUtils.getBean(implClass, MsgStrategy.class);
                ArgumentAssert.notNull(msgStrategy, "实现类[{}]不存在", implClass);
                result = msgStrategy.exec(msgParam);
            } else {
                /*
                 * 注意： 脚本中，不支持lombok注解
                 */
                msgStrategy = GlueFactory.getInstance().loadNewInstance(defInterface.getScript());
                ArgumentAssert.notNull(msgStrategy, "实现类不存在");
                result = msgStrategy.exec(msgParam);
            }

            boolean success = msgStrategy.isSuccess(result);
            if (success) {
                logging.setStatus(MsgInterfaceLoggingStatusEnum.SUCCESS.getValue());
                extendMsg.setStatus(TaskStatus.SUCCESS);
                interfaceLogManager.incrSuccessCount(extendInterfaceLog.getId());
            } else {
                extendMsg.setStatus(TaskStatus.FAIL);
                logging.setStatus(MsgInterfaceLoggingStatusEnum.FAIL.getValue());
                interfaceLogManager.incrFailCount(extendInterfaceLog.getId());
            }

            logging.setResult(JSONUtil.toJsonStr(result));

            extendMsg.setTitle(result.getTitle());
            extendMsg.setContent(result.getContent());
            msgManager.updateById(extendMsg);
        } catch (Exception e) {

            extendMsg.setStatus(TaskStatus.FAIL);
            msgManager.updateById(extendMsg);

            log.error("执行发送消息失败", e);
            logging.setStatus(MsgInterfaceLoggingStatusEnum.FAIL.getValue());
            logging.setErrorMsg(ExceptionUtil.getRootCauseMessage(e));
            interfaceLogManager.incrFailCount(extendInterfaceLog.getId());

        } finally {
            interfaceLoggingManager.save(logging);
        }
        return true;
    }

}
