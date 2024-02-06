package cn.lmx.kpu.oauth.event.listener;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.lmx.kpu.oauth.event.LoginEvent;
import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;
import cn.lmx.kpu.system.enumeration.system.LoginStatusEnum;
import cn.lmx.kpu.system.service.system.DefLoginLogService;
import cn.lmx.kpu.system.service.tenant.DefUserService;
import cn.lmx.kpu.system.vo.save.system.DefLoginLogSaveVO;

/**
 * 登录事件监听，用于记录登录日志
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginListener {
    private final DefLoginLogService defLoginLogService;
    private final DefUserService defUserService;

    @Async
    @EventListener({LoginEvent.class})
    public void saveSysLog(LoginEvent event) {
        LoginStatusDTO loginStatus = (LoginStatusDTO) event.getSource();

        if (LoginStatusEnum.SUCCESS.eq(loginStatus.getStatus())) {
            // 重置错误次数 和 最后登录时间
            this.defUserService.resetPassErrorNum(loginStatus.getUserId());
        } else if (LoginStatusEnum.PASSWORD_ERROR.eq(loginStatus.getStatus())) {
            // 密码错误
            this.defUserService.incrPasswordErrorNumById(loginStatus.getUserId());
        }
        DefLoginLogSaveVO saveVO = BeanUtil.toBean(loginStatus, DefLoginLogSaveVO.class);
        defLoginLogService.save(saveVO);
    }

}
