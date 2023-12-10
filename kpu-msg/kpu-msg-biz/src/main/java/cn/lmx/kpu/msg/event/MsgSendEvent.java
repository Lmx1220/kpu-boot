package cn.lmx.kpu.msg.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  22:18
 */
public class MsgSendEvent extends ApplicationEvent {
    public MsgSendEvent(Object source) {
        super(source);
    }
}
