package cn.lmx.kpu.msg.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登录事件
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
public class MsgSendEvent extends ApplicationEvent {
    public MsgSendEvent(MsgEventVO msg) {
        super(msg);
    }
}
