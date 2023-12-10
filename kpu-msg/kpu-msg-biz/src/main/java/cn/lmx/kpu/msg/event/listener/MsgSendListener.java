package cn.lmx.kpu.msg.event.listener;

import cn.lmx.kpu.msg.event.model.MsgEventVo;
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
public class MsgSendListener {

    @Async
    @EventListener({MsgEventVo.class})
    public void saveSysLog(MsgEventVo event) {

    }
}
