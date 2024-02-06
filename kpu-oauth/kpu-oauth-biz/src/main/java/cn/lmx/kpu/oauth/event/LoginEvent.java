package cn.lmx.kpu.oauth.event;

import org.springframework.context.ApplicationEvent;
import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;

/**
 * 登录事件
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
public class LoginEvent extends ApplicationEvent {
    public LoginEvent(LoginStatusDTO source) {
        super(source);
    }
}
