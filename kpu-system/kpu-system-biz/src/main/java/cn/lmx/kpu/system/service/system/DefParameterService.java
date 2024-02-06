package cn.lmx.kpu.system.service.system;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.system.entity.system.DefParameter;

/**
 * <p>
 * 业务接口
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefParameterService extends SuperCacheService<Long, DefParameter> {
    /**¬
     * 检测参数键是否可用
     *
     * @param key 健
     * @param id  参数ID
     * @return 是否存在
     */
    Boolean checkKey(String key, Long id);
}
