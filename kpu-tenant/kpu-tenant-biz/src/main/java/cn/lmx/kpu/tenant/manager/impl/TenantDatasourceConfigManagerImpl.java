package cn.lmx.kpu.tenant.manager.impl;


import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.tenant.dao.TenantDatasourceConfigMapper;
import cn.lmx.kpu.tenant.entity.TenantDatasourceConfig;
import cn.lmx.kpu.tenant.manager.TenantDatasourceConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 租户数据源关系
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
public class TenantDatasourceConfigManagerImpl extends SuperManagerImpl<TenantDatasourceConfigMapper, TenantDatasourceConfig> implements TenantDatasourceConfigManager {
}
