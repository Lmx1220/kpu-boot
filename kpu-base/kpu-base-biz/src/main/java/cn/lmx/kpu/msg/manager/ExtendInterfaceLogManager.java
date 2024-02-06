package cn.lmx.kpu.msg.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLog;

/**
 * <p>
 * 通用业务接口
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
public interface ExtendInterfaceLogManager extends SuperManager<ExtendInterfaceLog> {

    /***
     * 根据接口ID查询接口执行日志
     * @param interfaceId
     * @return
     */
    ExtendInterfaceLog getByInterfaceId(Long interfaceId);

    /**
     * 递增成功次数
     *
     * @param id 日志ID
     * @return
     */
    void incrSuccessCount(Long id);

    /**
     * 递增失败次数
     *
     * @param id 日志ID
     * @return
     */
    void incrFailCount(Long id);
}


