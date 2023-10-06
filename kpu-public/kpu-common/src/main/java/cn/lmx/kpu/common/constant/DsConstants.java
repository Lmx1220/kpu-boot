package cn.lmx.kpu.common.constant;

import static cn.lmx.basic.context.ContextConstants.TENANT_BASE_POOL_NAME_HEADER;
import static cn.lmx.basic.context.ContextConstants.TENANT_EXTEND_POOL_NAME_HEADER;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  16:31
 */
public interface DsConstants {
    String DEFAULTS = "0";
    String BASE_TENANT = "#thread." + TENANT_BASE_POOL_NAME_HEADER;
    String EXTEND_TENANT = "#thread." + TENANT_EXTEND_POOL_NAME_HEADER;

}
