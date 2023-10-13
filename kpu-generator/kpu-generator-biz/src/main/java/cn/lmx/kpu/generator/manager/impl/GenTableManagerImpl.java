package cn.lmx.kpu.generator.manager.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.DbPlusUtil;
import cn.lmx.kpu.generator.dao.GenTableMapper;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.manager.GenTableManager;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.manager.DatasourceConfigManager;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.druid.DruidConfig;
import com.baomidou.dynamic.datasource.exception.ErrorCreateDataSourceException;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTableManagerImpl extends SuperManagerImpl<GenTableMapper, GenTable> implements GenTableManager {

    private final DatasourceConfigManager datasourceConfigManager;


    private final DataSourceCreator druidDataSourceCreator;
    private final DynamicDataSourceProperties properties;
    private final DataSource dataSource;
    private final Map<String, DataSource> dsMap = new HashMap<>();

    @Override
    public DataSource getDs(Long dsId) {
        ArgumentAssert.notNull(dsId, "请先选择数据源:{}", dsId);
        DatasourceConfig datasourceConfig = datasourceConfigManager.getById(dsId);

        String key = datasourceConfig.getUrl() + datasourceConfig.getDriverClassName() + datasourceConfig.getUsername() + datasourceConfig.getPassword();
        if (dsMap.containsKey(key)) {
            return dsMap.get(key);
        }

        ArgumentAssert.notNull(datasourceConfig, "请先配置数据源:{}", dsId);
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl(datasourceConfig.getUrl());
        dataSourceProperty.setUsername(datasourceConfig.getUsername());
        dataSourceProperty.setPassword(datasourceConfig.getPassword());
        dataSourceProperty.setDriverClassName(datasourceConfig.getDriverClassName());
        DataSource ds = getDs(dataSourceProperty);
        dsMap.put(key, ds);
        return ds;
    }

    @Override
    public DbType getDbType() {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource primary = ds.getDataSource(null);
        return DbPlusUtil.getDbType(primary);
    }

    public DataSource getDs(DataSourceProperty dataSourceProperty) {
        dataSourceProperty.setSeata(false);
        dataSourceProperty.setDruid(BeanUtil.toBean(properties.getDruid(), DruidConfig.class));
        // 配置获取链接等待超时的时间
        dataSourceProperty.getDruid().setMaxWait(3000);
        // 配置初始化大小、最小、最大
        dataSourceProperty.getDruid().setInitialSize(1);
        dataSourceProperty.getDruid().setMinIdle(1);
        dataSourceProperty.getDruid().setMaxActive(1);
        // 链接错误重试次数
        dataSourceProperty.getDruid().setConnectionErrorRetryAttempts(0);
        // 获取失败后中断
        dataSourceProperty.getDruid().setBreakAfterAcquireFailure(true);
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSourceProperty.getDruid().setConnectionProperties(properties);

        try {
            return druidDataSourceCreator.createDataSource(dataSourceProperty);
        } catch (ErrorCreateDataSourceException e) {
            log.error("数据源初始化期间出现异常", e);
            throw new BizException("数据源初始化期间出现异常", e);
        } catch (Exception e) {
            log.error("创建测试链接错误 {}", dataSourceProperty.getUrl());
            throw new BizException("创建测试链接错误 " + dataSourceProperty.getUrl(), e);
        }
    }
}
