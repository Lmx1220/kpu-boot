package cn.lmx.kpu.generator.manager.impl;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.DbPlusUtil;
import cn.lmx.kpu.generator.entity.DefGenTable;
import cn.lmx.kpu.generator.manager.DefGenTableManager;
import cn.lmx.kpu.generator.mapper.DefGenTableMapper;
import cn.lmx.kpu.system.entity.tenant.DefDatasourceConfig;
import cn.lmx.kpu.system.manager.tenant.DefDatasourceConfigManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 通用业务实现类
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefGenTableManagerImpl extends SuperManagerImpl<DefGenTableMapper, DefGenTable> implements DefGenTableManager {

    private final DefDatasourceConfigManager defDatasourceConfigManager;
    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;
    private final DataSource dataSource;
    private final Map<String, DataSource> dsMap = new HashMap<>();

    @Override
    public DbType getDbType() {
        return DbPlusUtil.getDbType(dataSource);
    }

    @Override
    public DataSource getDs(Long dsId) {
        ArgumentAssert.notNull(dsId, "请先选择数据源:{}", dsId);
        DefDatasourceConfig defDatasourceConfig = defDatasourceConfigManager.getById(dsId);
        ArgumentAssert.notNull(defDatasourceConfig, "请先配置数据源:{}", dsId);

        String key = defDatasourceConfig.getUrl() + defDatasourceConfig.getDriverClassName() + defDatasourceConfig.getUsername() + defDatasourceConfig.getPassword();
        if (dsMap.containsKey(key)) {
            return dsMap.get(key);
        }

        String group = defDatasourceConfig.getName();
        Setting setting = Setting.create()
                .setByGroup("url", group, defDatasourceConfig.getUrl())
                .setByGroup("username", group, defDatasourceConfig.getUsername())
                .setByGroup("password", group, defDatasourceConfig.getPassword())
                .setByGroup("driver", group, defDatasourceConfig.getDriverClassName())
                .setByGroup("initialSize", group, "1")
                .setByGroup("maxActive", group, "1")
                .setByGroup("minIdle", group, "1")
                .setByGroup("validationQuery", group, validationQuery)
                .setByGroup("connectionErrorRetryAttempts", group, "0")
                .setByGroup("breakAfterAcquireFailure", group, "true")
                // 5.7 版本支持注释
                .setByGroup("useInformationSchema", group, "true")
                .setByGroup("remarks", group, "true");
        DSFactory dsFactory = DSFactory.create(setting);
        DataSource ds = dsFactory.getDataSource(group);
        dsMap.put(key, ds);
        return ds;
    }

}
