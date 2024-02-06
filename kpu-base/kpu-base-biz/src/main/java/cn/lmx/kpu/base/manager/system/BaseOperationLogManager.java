package cn.lmx.kpu.base.manager.system;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.base.entity.system.BaseOperationLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务接口
 * 操作日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BaseOperationLogManager extends SuperManager<BaseOperationLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
