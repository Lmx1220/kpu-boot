package cn.lmx.kpu.base.service;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.base.entity.user.BaseEmployee;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
public interface BaseEmployeeTestService extends SuperCacheManager<BaseEmployee> {
    /**
     * 单体查询
     *
     * @param id id
     * @return cn.lmx.kpu.base.entity.user.BaseEmployee
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    BaseEmployee get(Long id);
}
