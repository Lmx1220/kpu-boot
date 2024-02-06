package cn.lmx.kpu.system.service.system;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.system.entity.system.DefDict;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 字典
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefDictService extends SuperService<Long, DefDict> {

    /**
     * 检查字典标识是否可用
     *
     * @param key 标识
     * @param id  当前id
     * @return
     */
    boolean checkByKey(String key, Long id);

    /**
     * 删除字典
     *
     * @param ids
     * @return
     */
    Boolean deleteDict(List<Long> ids);

    /**
     * 复制
     *
     * @param id 字典id
     * @return cn.lmx.kpu.system.entity.system.DefDict
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    @Override
    DefDict copy(Long id);

    /**
     * 根据字典id查询字典项列表
     *
     * @param id 字典ID
     * @return java.util.List<cn.lmx.kpu.tenant.entity.base.DefDict>
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    List<DefDict> findItemByDictId(Long id);
}
