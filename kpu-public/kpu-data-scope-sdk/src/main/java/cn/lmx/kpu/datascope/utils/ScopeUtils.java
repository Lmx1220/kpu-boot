package cn.lmx.kpu.datascope.utils;

import cn.hutool.core.collection.CollUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.annotation.DataField;
import cn.lmx.kpu.common.annotation.DataScope;
import cn.lmx.kpu.datascope.model.DataFieldProperty;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
public class ScopeUtils {
    private static final Map<String, List<DataFieldProperty>> DFP_MAP = new ConcurrentHashMap<>();

    private static String getMapper(String msId) {
        int lastIndex = msId.lastIndexOf(".");
        return lastIndex > 0 ? msId.substring(0, lastIndex) : null;
    }


    public static List<DataFieldProperty> buildDataFieldProperty(DataField[] dfs) {
        if (dfs != null && dfs.length > 0) {
            return Arrays.stream(dfs).map(df -> new DataFieldProperty(df.alias())).toList();
        }
        return Collections.emptyList();
    }

    @SneakyThrows
    public static List<DataFieldProperty> buildDataScopeProperty(String msId) {
        List<DataFieldProperty> dfpList = DFP_MAP.get(msId);
        if (CollUtil.isNotEmpty(dfpList)) {
            return dfpList;
        }

        String mapperFullPath = getMapper(msId);
        if (mapperFullPath == null) {
            return Collections.emptyList();
        }

        Class mapperClazz = Class.forName(mapperFullPath);
        Method[] methods = mapperClazz.getMethods();

        for (Method method : methods) {
            DataScope ds = method.getAnnotation(DataScope.class);
            if (ds != null && !ds.ignore()) {
                List<DataFieldProperty> dfps = buildDataFieldProperty(ds.value());
                DFP_MAP.put(mapperFullPath + StrPool.DOT + method.getName(), dfps);
            }
        }
        return DFP_MAP.get(msId);
    }
}
