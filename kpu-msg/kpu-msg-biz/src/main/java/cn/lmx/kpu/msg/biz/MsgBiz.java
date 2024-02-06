package cn.lmx.kpu.msg.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.service.*;
import cn.lmx.kpu.msg.strategy.MsgContext;

import cn.lmx.kpu.msg.vo.update.MsgPublishVO;
import cn.lmx.kpu.msg.vo.update.MsgSendVO;
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
        Msg extendMsg = msgService.getById(id);
        ArgumentAssert.notNull(extendMsg, "请先保存消息");

        // 先查租户库，在查默认库
        MsgTemplate extendMsgTemplate = msgTemplateService.getByCode(extendMsg.getTemplateCode());
        ArgumentAssert.notNull(extendMsgTemplate, "请配置消息模板");

        Interface defInterface = interfaceService.getById(extendMsgTemplate.getInterfaceId());
        ArgumentAssert.notNull(defInterface, "请配置消息接口：{}", extendMsgTemplate.getType());
        // 先查租户库，在查默认库
        Map<String, Object> propertyParams = interfacePropertyService.listByInterfaceId(defInterface.getId());
        List<MsgRecipient> recipientList = msgRecipientService.listByMsgId(id);

        return msgContext.execSend(extendMsg, extendMsgTemplate, recipientList, defInterface, propertyParams);
    }
    /**
     * 发送消息
     *
     * @param data
     * @return
     */
    public Boolean sendByTemplate(MsgSendVO data, SysUser sysUser) {
        MsgTemplate msgTemplate = validAndInit(data);
        return msgService.send(data, msgTemplate, sysUser);
    }

    /**
     * 验证数据，并初始化数据
     */
    private MsgTemplate validAndInit(MsgSendVO msgSaveVO) {
        ArgumentAssert.notEmpty(msgSaveVO.getTemplateCode(), "请选择消息模板");

        MsgTemplate msgTemplate = null;
        if (StrUtil.isNotEmpty(msgSaveVO.getTemplateCode())) {
            msgTemplate = msgTemplateService.getByCode(msgSaveVO.getTemplateCode());
        }
        ArgumentAssert.notNull(msgTemplate, "请选择正确的消息模板");

        //1，验证必要参数
        ArgumentAssert.notEmpty(msgSaveVO.getRecipientList(), "请填写消息接收人");

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (msgSaveVO.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(msgSaveVO.getSendTime());
            ArgumentAssert.isTrue(flag, "定时发送时间至少在当前时间的5分钟之后");
        }

        if (CollUtil.isEmpty(msgSaveVO.getRecipientList())) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "接收人不能为空");
        }

        return msgTemplate;
    }
    /**
     * 发布消息
     *
     * @param data    data
     * @param sysUser sysUser
     * @return java.lang.Boolean
     * @author lmx
     * @date 2023/12/10  22:56
     */
    public Boolean publish(MsgPublishVO data, SysUser sysUser) {
        //1，验证必要参数
        ArgumentAssert.notEmpty(data.getRecipientList(), "请填写消息接收人");
        ArgumentAssert.notEmpty(data.getTitle(), "请填写标题");
        ArgumentAssert.notEmpty(data.getContent(), "请填写内容");

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (data.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(data.getSendTime());
            ArgumentAssert.isTrue(flag, "定时发送时间至少在当前时间的5分钟之后");
        }

        if (CollUtil.isEmpty(data.getRecipientList())) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "接收人不能为空");
        }

        if (data.getContent().length() > 2147483647) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过2147483647字");
        }

        return msgService.publish(data, sysUser);
    }
}
