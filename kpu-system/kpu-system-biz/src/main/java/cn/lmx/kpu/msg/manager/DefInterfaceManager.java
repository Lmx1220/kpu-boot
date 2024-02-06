package cn.lmx.kpu.msg.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.msg.entity.DefInterface;

/**
 * <p>
 * 通用业务接口
 * 接口
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
public interface DefInterfaceManager extends SuperManager<DefInterface> {

    /**
     * 根据类型查询接口
     *
     * @param type
     * @return
     */
    DefInterface getByType(String type);
}


