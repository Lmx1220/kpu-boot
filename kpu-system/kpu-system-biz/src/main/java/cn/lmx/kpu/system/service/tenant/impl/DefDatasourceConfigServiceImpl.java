package cn.lmx.kpu.system.service.tenant.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.system.entity.tenant.DefDatasourceConfig;
import cn.lmx.kpu.system.manager.tenant.DefDatasourceConfigManager;
import cn.lmx.kpu.system.service.tenant.DefDatasourceConfigService;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefDatasourceConfigServiceImpl extends SuperServiceImpl<DefDatasourceConfigManager, Long, DefDatasourceConfig>
        implements DefDatasourceConfigService {

    @Override
    public Boolean testConnection(Long id) {
        return true;
    }
}
