package cn.lmx.kpu.generator.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.generator.entity.DefGenTableColumn;

import java.util.Collection;

/**
 * <p>
 * 通用业务接口
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefGenTableColumnManager extends SuperManager<DefGenTableColumn> {
    /**
     * 根据 生成表ID 删除字段
     *
     * @param idList idList
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    boolean removeByTableIds(Collection<Long> idList);
}
