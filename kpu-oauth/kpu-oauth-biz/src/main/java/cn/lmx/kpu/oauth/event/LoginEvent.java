package cn.lmx.kpu.oauth.event;

import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 登录事件
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class LoginEvent extends ApplicationEvent {
    public LoginEvent(LoginStatusDTO source) {
        super(source);
    }
}
