package cn.lmx.kpu.tenant.service;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  15:42
 */
public interface DataSourceService {
    // 加载系统内置租户数据源
    boolean loadSystemDataSource();

    // 加载自定义租户数据源
    boolean loadCustomDataSource();

    boolean initDataSource(Long tenantId);
}
