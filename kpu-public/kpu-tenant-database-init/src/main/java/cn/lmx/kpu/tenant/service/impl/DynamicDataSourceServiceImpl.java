package cn.lmx.kpu.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.context.ContextConstants;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.DbPlusUtil;
import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import cn.lmx.kpu.model.enumeration.system.TenantStatusEnum;
import cn.lmx.kpu.tenant.dao.InitDbMapper;
import cn.lmx.kpu.tenant.dto.DatasourceConfigBO;
import cn.lmx.kpu.tenant.dto.TenantBo;
import cn.lmx.kpu.tenant.dynamic.processor.DsThreadProcessor;
import cn.lmx.kpu.tenant.service.DataSourceService;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.DatasourceInitProperties;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.exception.ErrorCreateDataSourceException;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  15:46
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DynamicDataSourceServiceImpl implements DataSourceService {
    private static final String SCHEMA_PATH = "schema/{}/{}.sql";
    private static final String DATA_PATH = "data/{}/{}.sql";
    private final InitDbMapper initDbMapper;
    private final DynamicDataSourceProperties properties;
    private final DatabaseProperties databaseProperties;
    private final DefaultDataSourceCreator druidDataSourceCreator;
    private final DataSource dataSource;

    private DbType getDbType() {
        return DbPlusUtil.getDbType(dataSource);
    }

    private boolean addSystem(Long tenantId, boolean isInitSchema, boolean isNotErrorRetry) {
        DataSourceProperty defDataSourceProperty = properties.getDatasource().get(ContextConstants.DEF_TENANT_ID_STR);
        ArgumentAssert.notNull(defDataSourceProperty, "请先配置默认[{}]数据源", ContextConstants.DEF_TENANT_ID_STR);

        // 读取kpu.database.initDatabasePrefix 配置的租户前缀，动态初始化数据库
        databaseProperties.getInitDatabasePrefix().forEach(database -> {
            // 在程序启动时配置的默认库 数据源配置的基础上，修改租户库自己的特殊配置
            DataSourceProperty newDataSourceProperty = BeanUtil.toBean(defDataSourceProperty, DataSourceProperty.class);
            newDataSourceProperty.setPoolName(DsThreadProcessor.getPoolName(database, String.valueOf(tenantId)));
            if (DbType.ORACLE.getDb().equals(getDbType().getDb())) {
                newDataSourceProperty.setUsername(newDataSourceProperty.getPoolName());
                newDataSourceProperty.setPassword(newDataSourceProperty.getPoolName());
            } else {
                String oldDatabase = DbPlusUtil.getDataBaseNameByUrl(defDataSourceProperty.getUrl());
                String newDatabase = StrUtil.join(StrUtil.UNDERLINE, database, tenantId);
                newDataSourceProperty.setUrl(StrUtil.replace(defDataSourceProperty.getUrl(), oldDatabase, newDatabase));
            }
            if (isInitSchema) {
                DatasourceInitProperties init = newDataSourceProperty.getInit();
                if (init == null) {
                    init = new DatasourceInitProperties();
                }
                // 待创建的表结构
                init.setSchema(StrUtil.format(SCHEMA_PATH, getDbType().getDb(), database));
                // 待初始化的数据
                init.setData(StrUtil.format(DATA_PATH, getDbType().getDb(), database));
                newDataSourceProperty.setInit(init);
            }
            newDataSourceProperty.setSeata(databaseProperties.getIsSeata());
            newDataSourceProperty.setDruid(properties.getDruid());
            if (isNotErrorRetry) {
                // 链接错误重试次数
                newDataSourceProperty.getDruid().setConnectionErrorRetryAttempts(0);
                // 获取失败后中断
                newDataSourceProperty.getDruid().setBreakAfterAcquireFailure(true);
            }

            // 动态新增数据源
            putDs(newDataSourceProperty);
        });
        return true;
    }

    private Set<String> putDs(DataSourceProperty dsp) {
        try {
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) this.dataSource;
            DataSource newDataSource = druidDataSourceCreator.createDataSource(dsp);
            ds.addDataSource(dsp.getPoolName(), newDataSource);
            return ds.getDataSources().keySet();
        } catch (ErrorCreateDataSourceException e) {
            log.error("数据源初始化期间出现异常", e);
            throw new BizException("数据源初始化期间出现异常", e);
        }
    }

    // 加载系统内置租户数据源
    @Override
    public boolean loadSystemDataSource() {
        // 查询所有可用的租户
        List<String> status = Arrays.asList(TenantStatusEnum.NORMAL.getCode(), TenantStatusEnum.WAIT_INIT_DATASOURCE.getCode());
        List<Long> list = initDbMapper.selectTenantCodeList(status, TenantConnectTypeEnum.SYSTEM.name());

        // 循环初始化系统内置数据源
        list.forEach(tenantId -> addSystem(tenantId, false, false));
        return true;
    }

    // 加载自定义租户数据源
    @Override
    public boolean loadCustomDataSource() {
        // 查询所有可用的租户
        List<String> status = Arrays.asList(TenantStatusEnum.NORMAL.getCode(), TenantStatusEnum.WAIT_INIT_DATASOURCE.getCode());
        List<DatasourceConfigBO> dcList = initDbMapper.selectDataSourceConfig(status, TenantConnectTypeEnum.CUSTOM.name());

        // 循环初始化自定义数据源
        return addCustom(dcList, true, false);
    }

    @Override
    public boolean initDataSource(Long tenantId) {
        TenantBo tenant = initDbMapper.getTenantBy(tenantId);
        ArgumentAssert.notNull(tenant, "你需要初始化的租户不存在");
        if (TenantConnectTypeEnum.SYSTEM.eq(tenant.getConnectType())) {
            List<DatasourceConfigBO> dcList = initDbMapper.selectDataSourceConfigByTenantId(tenantId);
            return addCustom(dcList, false, true);
        } else {
            return addSystem(tenantId, true, true);
        }
    }

    private boolean addCustom(List<DatasourceConfigBO> dcList, boolean isInitSchema, boolean isNotErrorRetry) {
        DataSourceProperty defDataSourceProperty = properties.getDatasource().get(ContextConstants.DEF_TENANT_ID_STR);
        ArgumentAssert.notNull(defDataSourceProperty, "请先配置默认[{}]数据源", ContextConstants.DEF_TENANT_ID_STR);
        ArgumentAssert.notNull(dcList, "没有可用的租户数据源");
        dcList.forEach(dc -> {
            // 在程序启动时配置的默认库 数据源配置的基础上，修改租户库自己的特殊配置
            DataSourceProperty newDataSourceProperty = BeanUtil.toBean(defDataSourceProperty, DataSourceProperty.class);
            newDataSourceProperty.setPoolName(DsThreadProcessor.getPoolName(dc.getDbPrefix(), Convert.toStr(dc.getTenantId())));
            newDataSourceProperty.setUrl(dc.getUrl());
            newDataSourceProperty.setUsername(dc.getUsername());
            newDataSourceProperty.setPassword(dc.getPassword());
            newDataSourceProperty.setDriverClassName(dc.getDriverClassName());
            if (isInitSchema) {
                DatasourceInitProperties init = newDataSourceProperty.getInit();
                if (init == null) {
                    init = new DatasourceInitProperties();
                }
                String database = DbPlusUtil.getDataBaseNameByUrl(dc.getUrl());
                // 待创建的表结构
                init.setSchema(StrUtil.format(SCHEMA_PATH, getDbType().getDb(), ContextConstants.TENANT_BASE_POOL_NAME_HEADER));
                // 待初始化的数据
                init.setData(StrUtil.format(DATA_PATH, getDbType().getDb(), ContextConstants.TENANT_BASE_POOL_NAME_HEADER));
                newDataSourceProperty.setInit(init);
            }
            newDataSourceProperty.setSeata(databaseProperties.getIsSeata());
            newDataSourceProperty.setDruid(properties.getDruid());
            if (isNotErrorRetry) {
                // 链接错误重试次数
                newDataSourceProperty.getDruid().setConnectionErrorRetryAttempts(0);
                // 获取失败后中断
                newDataSourceProperty.getDruid().setBreakAfterAcquireFailure(true);
            }

            // 动态新增数据源
            putDs(newDataSourceProperty);
        });
        return true;
    }
}

