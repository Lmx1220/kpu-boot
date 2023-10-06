package cn.lmx.kpu.tenant.strategy;

import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.tenant.dto.TenantConnectDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 初始化系统上下文
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component
public class InitSystemContext {
    private final Map<String, InitSystemStrategy> initSystemStrategyMap = new ConcurrentHashMap<>();
    private final DatabaseProperties databaseProperties;

    public InitSystemContext(Map<String, InitSystemStrategy> strategyMap, DatabaseProperties databaseProperties) {
        strategyMap.forEach(this.initSystemStrategyMap::put);
        this.databaseProperties = databaseProperties;
    }

    /**
     * 初始化链接
     *
     * @param tenantConnect 链接参数
     */
    public boolean initConnect(TenantConnectDTO tenantConnect) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();

        return initSystemStrategy.initConnect(tenantConnect);
    }

    private InitSystemStrategy getInitSystemStrategy() {
        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
        ArgumentAssert.notNull(initSystemStrategy, "您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name());
        return initSystemStrategy;
    }

    /**
     * 重置系统
     *
     * @param tenant 租户编码
     */
    public boolean reset(String tenant) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();
        return initSystemStrategy.reset(tenant);
    }

    /**
     * 删除租户数据
     *
     * @param tenantCodeList 租户编码
     */
    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();

        return initSystemStrategy.delete(ids, tenantCodeList);
    }
}
