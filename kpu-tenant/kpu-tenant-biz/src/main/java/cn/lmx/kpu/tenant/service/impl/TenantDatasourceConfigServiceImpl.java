package cn.lmx.kpu.tenant.service.impl;


import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.kpu.tenant.dao.TenantDatasourceConfigMapper;
import cn.lmx.kpu.tenant.entity.TenantDatasourceConfig;
import cn.lmx.kpu.tenant.service.TenantDatasourceConfigService;
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
public class TenantDatasourceConfigServiceImpl extends SuperServiceImpl<TenantDatasourceConfigMapper, TenantDatasourceConfig> implements TenantDatasourceConfigService {
}
