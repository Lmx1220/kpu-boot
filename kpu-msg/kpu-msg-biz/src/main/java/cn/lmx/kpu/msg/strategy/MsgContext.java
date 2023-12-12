package cn.lmx.kpu.msg.strategy;


import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.msg.entity.*;
import cn.lmx.kpu.msg.enumeration.InterfaceExecModeEnum;
import cn.lmx.kpu.msg.glue.GlueFactory;
import cn.lmx.kpu.msg.mapper.MsgTemplateMapper;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.utils.ArgumentAssert;

import java.util.List;
import java.util.Map;

/**
 * 短信发送上下文
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Component

public class MsgContext {

    /**
     * 根据任务id发送短信
     * <p>
     * 待完善的点：
     * 1， 查询次数过多，想办法优化
     *
     * @param taskId 任务id
     * @return 任务id
     */
//    @Async
//    @Transactional(rollbackFor = Exception.class)
//    public void execSend(Long taskId) {
//
//        Task msgTask = msgTaskMapper.selectById(taskId);
//        ArgumentAssert.notNull(msgTask, "短信任务尚未保存成功");
//
//        Template template = msgTemplateMapper.selectById(msgTask.getTemplateId());
//        ArgumentAssert.notNull(template, "短信模板为空");
//
//        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
//        SmsStrategy msgStrategy = msgContextStrategyMap.get(template.getProviderType().name());
//        ArgumentAssert.notNull(msgStrategy, "短信供应商不存在");
//
//        msgStrategy.sendSms(msgTask, template);
//    }
//    @Async
    @Transactional(rollbackFor = Exception.class)
    public boolean execSend(Msg msg, MsgTemplate msgTemplate, List<MsgRecipient> recipientList, Interface byId, Map<String, Object> propertyParams)  {
        MsgParam msgParam = new MsgParam();
        msgParam.setMsg(msg);
        msgParam.setRecipientList(recipientList);
        msgParam.setExtendMsgTemplate(msgTemplate);
        msgParam.setPropertyParams(propertyParams);
        MsgResult result;
        if (InterfaceExecModeEnum.IMPL_CLASS.eq(byId.getExecMode())) {
            // 实现类
            String implClass = byId.getImplClass();
            MsgStrategy msgStrategy = SpringUtils.getBean(implClass, MsgStrategy.class);
            ArgumentAssert.notNull(msgStrategy, "实现类[{}]不存在", implClass);
            result = msgStrategy.exec(msgParam);
        }else {
            // 脚本
            MsgStrategy msgStrategy = GlueFactory.getInstance().loadNewInstance(byId.getScript());
            ArgumentAssert.notNull(msgStrategy, "实现类不存在");
            result = msgStrategy.exec(msgParam);
        }


        result.getResult();
        return  true ;
    }
}
