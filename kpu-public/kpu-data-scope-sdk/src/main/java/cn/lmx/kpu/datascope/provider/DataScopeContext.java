package cn.lmx.kpu.datascope.provider;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import cn.lmx.basic.exception.BizException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Component
public class DataScopeContext {
    private final Map<String, DataScopeProvider> dspMap = new ConcurrentHashMap<>();

    public DataScopeContext(Map<String, DataScopeProvider> dspMap) {
        this.dspMap.putAll(dspMap);
    }

    public DataScopeProvider getDataScopeProvider(String type) {
        String key = StrUtil.startWith(type, "DATA_SCOPE_") ? type : "DATA_SCOPE_" + type;
        DataScopeProvider dataScopeProvider = dspMap.get(key);
        if (dataScopeProvider == null) {
            throw BizException.wrap("请先创建数据权限的实现类，使其 implements DataScopeProvider，并添加注解：@Component(\"{}\")", key);
        }
        return dataScopeProvider;
    }

}
