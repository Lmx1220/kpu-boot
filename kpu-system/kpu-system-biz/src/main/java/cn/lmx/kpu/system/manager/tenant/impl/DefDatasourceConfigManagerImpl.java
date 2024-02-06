package cn.lmx.kpu.system.manager.tenant.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.system.entity.tenant.DefDatasourceConfig;
import cn.lmx.kpu.system.manager.tenant.DefDatasourceConfigManager;
import cn.lmx.kpu.system.mapper.tenant.DefDatasourceConfigMapper;

/**
 * 应用管理
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class DefDatasourceConfigManagerImpl extends SuperManagerImpl<DefDatasourceConfigMapper, DefDatasourceConfig> implements DefDatasourceConfigManager {
    @Override
    public DefDatasourceConfig getByName(String dsName) {
        return getOne(Wraps.<DefDatasourceConfig>lbQ().eq(DefDatasourceConfig::getName, dsName), false);
    }
}
