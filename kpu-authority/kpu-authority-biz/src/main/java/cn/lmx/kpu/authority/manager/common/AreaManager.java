package cn.lmx.kpu.authority.manager.common;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.authority.entity.common.Area;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface AreaManager extends SuperCacheManager<Area> {

    /**
     * 递归删除
     *
     * @param ids 地区id
     * @return 是否成功
     */
    boolean recursively(List<Long> ids);
}
