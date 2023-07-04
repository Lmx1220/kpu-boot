package cn.lmx.kpu.authority.event.listener;

import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.authority.event.ParameterUpdateEvent;
import cn.lmx.kpu.authority.event.model.ParameterUpdate;
import cn.lmx.kpu.authority.service.auth.OnlineService;
import cn.lmx.kpu.common.constant.ParameterKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
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
public class ParameterUpdateListener {

    private final OnlineService onlineService;

    @Async
    @EventListener({ParameterUpdateEvent.class})
    public void saveSysLog(ParameterUpdateEvent event) {
        ParameterUpdate source = (ParameterUpdate) event.getSource();

        ContextUtil.setTenant(source.getTenant());
        if (ParameterKey.LOGIN_POLICY.equals(source.getKey())) {
            onlineService.reset();
        }
    }
}
