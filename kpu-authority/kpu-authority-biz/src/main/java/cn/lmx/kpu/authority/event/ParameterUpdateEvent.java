package cn.lmx.kpu.authority.event;

import cn.lmx.kpu.authority.event.model.ParameterUpdate;
import org.springframework.context.ApplicationEvent;

/**
 * 登录事件
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class ParameterUpdateEvent extends ApplicationEvent {
    public ParameterUpdateEvent(ParameterUpdate source) {
        super(source);
    }
}
