package cn.lmx.kpu.msg.strategy.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.kpu.msg.enumeration.NoticeRemindModeEnum;
import cn.lmx.kpu.msg.manager.NoticeManager;
import cn.lmx.kpu.msg.strategy.MsgStrategy;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;
import cn.lmx.kpu.msg.ws.WebSocketSubject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zuihou
 * @date 2022/7/11 0011 10:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeMsgStrategyImpl implements MsgStrategy {
    private final NoticeManager noticeManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsgResult exec(MsgParam msgParam) {
        Msg msg = msgParam.getMsg();
        List<MsgRecipient> recipientList = msgParam.getRecipientList();
        MsgTemplate msgTemplate = msgParam.getMsgTemplate();

        MsgResult msgResult = replaceVariable(msg, msgTemplate);

        List<Notice> list = new ArrayList<>();
        for (MsgRecipient MsgRecipient : recipientList) {
            Notice notice = new Notice();
            BeanUtil.copyProperties(msgTemplate, notice);
            BeanUtil.copyProperties(msg, notice);

            notice.setRecipientId(Long.valueOf(MsgRecipient.getRecipient()));
            notice.setTitle(msgResult.getTitle());
            notice.setContent(msgResult.getContent());
            notice.setIsHandle(false);
            notice.setIsRead(false);
            notice.setHandleTime(null);
            notice.setReadTime(null);
            notice.setId(null);
            notice.setRemindMode(NoticeRemindModeEnum.NOTICE.getCode());
            notice.setMsgId(msg.getId());
            list.add(notice);

            WebSocketSubject subject = WebSocketSubject.Holder.getSubject(Long.valueOf(MsgRecipient.getRecipient()));
            // 通知客户端 接收消息
            subject.notify("1", null);
        }
        noticeManager.saveBatch(list);
        return msgResult.setResult(true);
    }

    @Override
    public boolean isSuccess(MsgResult result) {
        return (boolean) result.getResult();
    }
}
