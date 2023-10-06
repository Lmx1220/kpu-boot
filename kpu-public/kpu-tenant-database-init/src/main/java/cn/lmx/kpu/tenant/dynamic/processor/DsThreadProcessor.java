package cn.lmx.kpu.tenant.dynamic.processor;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.context.ContextUtil;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  16:54
 */
public class DsThreadProcessor extends DsProcessor {
    public static final String HEADER_PREFIX = "#thread";
    public static final String POOL_NAME = "{}_{}";

    public static String getPoolName(String prefix, String tenantId) {
        return StrUtil.format(POOL_NAME, prefix, tenantId);
    }

    @Override
    public boolean matches(String key) {
        return key.startsWith(HEADER_PREFIX);
    }

    @Override
    public String doDetermineDatasource(MethodInvocation invocation, String key) {
        String prefix = key.substring(HEADER_PREFIX.length() + 1);
        String tenantId = ContextUtil.get(prefix);
        return getPoolName(prefix, tenantId);
    }
}
