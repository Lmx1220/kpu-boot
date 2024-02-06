package cn.lmx.kpu.system.manager.system;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.system.entity.system.DefLoginLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务接口
 * 登录日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefLoginLogManager extends SuperManager<DefLoginLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
