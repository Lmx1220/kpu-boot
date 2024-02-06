package cn.lmx.kpu.msg.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.msg.entity.InterfaceLog;

/**
 * <p>
 * 通用业务接口
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface InterfaceLogManager extends SuperManager<InterfaceLog> {

    /***
     * 根据接口ID查询接口执行日志
     * @param interfaceId
     * @return
     */
    InterfaceLog getByInterfaceId(Long interfaceId);

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


