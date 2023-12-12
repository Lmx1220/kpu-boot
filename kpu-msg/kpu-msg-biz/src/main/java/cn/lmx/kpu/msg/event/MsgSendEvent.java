package cn.lmx.kpu.msg.event;

import cn.lmx.kpu.msg.event.model.MsgEventVo;
import org.springframework.context.ApplicationEvent;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  22:18
 */
public class MsgSendEvent extends ApplicationEvent {
    public MsgSendEvent(MsgEventVo source) {
        super(source);
    }
}
