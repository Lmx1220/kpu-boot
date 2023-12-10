package cn.lmx.kpu.msg.biz;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.event.MsgSendEvent;
import cn.lmx.kpu.msg.service.*;
import cn.lmx.kpu.msg.strategy.MsgContext;
import cn.lmx.kpu.msg.strategy.domain.MsgPublishVO;
import cn.lmx.kpu.msg.strategy.domain.MsgSendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static cn.lmx.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  22:56
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MsgBiz {
    private final MsgService msgService;

    private final MsgTemplateService msgTemplateService;
    private final InterfaceService interfaceService;
    private final InterfacePropertyService interfacePropertyService;
    private final MsgRecipientService msgRecipientService;
    private final MsgContext msgContext;


    /**
     * 执行消息发送
     *
     * @param id 消息id
     * @return
     */
    public boolean execSend(Long id) {
        Msg msg = msgService.getById(id);
        ArgumentAssert.notNull(msg, "请先保存消息");
        MsgTemplate msgTemplate = msgTemplateService.getByCode(msg.getTemplateCode());

        ArgumentAssert.notNull(msgTemplate, "请先保存消息模板");
        Interface byId = interfaceService.getById(msgTemplate.getInterfaceId());
        ArgumentAssert.notNull(byId, "请配置接口：{}", msgTemplate.getType());
        Map<String,Object> propertyParams= interfacePropertyService.listByInterfaceId(byId.getId());
       List<MsgRecipient> recipientList =msgRecipientService.listByMsgId(id);
       return msgContext.execSend(msg,msgTemplate,recipientList,byId,propertyParams);
    }
    public boolean sendByTemplate(MsgSendVO data, SysUser sysUser) {
        MsgTemplate msgTemplate = validAndInit(data);
        return msgService.send(data,msgTemplate,sysUser);
    }

    private MsgTemplate validAndInit(MsgSendVO data) {
    }

    public Boolean publish(MsgPublishVO data, SysUser sysUser) {
        ArgumentAssert.notEmpty(data.getRecipientList(), "请填写消息收人");
        ArgumentAssert.notEmpty(data.getTitle(), "请填写标题");
        ArgumentAssert.notEmpty(data.getContent(), "请填写内容");

        //
        if (data.getSendTime() !=null){
            boolean flg = LocalDateTime.now().plusMinutes(4).isBefore(data.getSendTime());
            ArgumentAssert.isTrue(flg, "定时发送时间至少在当前时间的5分钟后");
        }
        if (CollUtil.isEmpty(data.getRecipientList())){
            throw new BizException(BASE_VALID_PARAM.getCode(),"接受人不能为空");
        }
        if (data.getContent().length() > 2147483647){
            throw new BizException(BASE_VALID_PARAM.getCode(),"发送内容不能超过2147483647");
        }
        return msgService.publish(data,sysUser);
    }
}
