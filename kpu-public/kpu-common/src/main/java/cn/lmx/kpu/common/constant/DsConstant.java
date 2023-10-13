package cn.lmx.kpu.common.constant;

import cn.lmx.basic.context.ContextConstants;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/11  01:43
 */
public interface DsConstant {
    /**
     * 默认数据源
     */
    String DEFAULTS = "0";
    /**
     * [内置常量] 动态租户数据源
     */
    String BASE_TENANT = "#thread." + ContextConstants.TENANT_BASE_POOL_NAME_HEADER;
    String EXTEND_TENANT = "#thread." + ContextConstants.TENANT_EXTEND_POOL_NAME_HEADER;
    // [自行新增]
    String XXX_TENANT = "#thread." + ContextConstants.TENANT_XXX_POOL_NAME_HEADER;
}

