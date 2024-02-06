package cn.lmx.kpu.msg.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import cn.lmx.kpu.msg.biz.MsgBiz;
import cn.lmx.kpu.msg.event.MsgEventVO;
import cn.lmx.kpu.msg.event.MsgSendEvent;

/**
 * 消息发送事件监听
 * 目的： 解耦
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MsgSendListener {
    private final MsgBiz msgBiz;

    @Async
    @TransactionalEventListener({MsgSendEvent.class})
    public void handleMsg(MsgSendEvent event) {
        MsgEventVO msgEventVO = (MsgEventVO) event.getSource();
        msgEventVO.write();
        msgBiz.execSend(msgEventVO.getMsgId());
    }

}
