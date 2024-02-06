package cn.lmx.kpu.system.manager.system;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.interfaces.echo.LoadService;
import cn.lmx.kpu.system.entity.system.DefParameter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务接口
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefParameterManager extends SuperCacheManager<DefParameter>, LoadService {
    /**
     * 根据参数key查参数值
     * <p>
     * 1. 先查询租户自己的参数。
     * 2. 若不存在，则查询系统默认的参数。
     *
     * @param paramsKeys 参数key
     * @return key： 参数key  value: 参数值
     */
    Map<String, String> findParamMapByKey(List<String> paramsKeys);
}
