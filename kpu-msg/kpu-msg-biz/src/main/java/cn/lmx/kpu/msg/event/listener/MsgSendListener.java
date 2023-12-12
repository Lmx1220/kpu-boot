package cn.lmx.kpu.msg.event.listener;

import cn.lmx.kpu.msg.biz.MsgBiz;
import cn.lmx.kpu.msg.event.MsgSendEvent;
import cn.lmx.kpu.msg.event.model.MsgEventVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 参数修改事件监听，用于调整具体的业务
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MsgSendListener {
    private final MsgBiz msgBiz;
    @Async
    @Order
    @EventListener({MsgSendEvent.class})
    public void saveSysLog(MsgSendEvent event) {
        MsgEventVo source = (MsgEventVo) event.getSource();
        msgBiz.execSend(source.getMsgId());

    }
}
