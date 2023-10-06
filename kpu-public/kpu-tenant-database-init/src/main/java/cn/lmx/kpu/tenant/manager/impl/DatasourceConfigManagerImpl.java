package cn.lmx.kpu.tenant.manager.impl;


import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.tenant.dao.DatasourceConfigMapper;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.manager.DatasourceConfigManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceConfigManagerImpl extends SuperManagerImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigManager {


}
