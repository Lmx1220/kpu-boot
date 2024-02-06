package cn.lmx.kpu.system.service.system;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.system.entity.system.DefLoginLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务接口
 * 登录日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefLoginLogService extends SuperService<Long, DefLoginLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
