package cn.lmx.kpu.system.service.system;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.system.entity.system.DefDict;

/**
 * <p>
 * 业务接口
 * 字典
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefDictItemService extends SuperService<Long, DefDict> {

    /**
     * 检查字典项标识是否可用
     *
     * @param key    标识
     * @param dictId 所属字典id
     * @param id     当前id
     * @return
     */
    boolean checkItemByKey(String key, Long dictId, Long id);


}
