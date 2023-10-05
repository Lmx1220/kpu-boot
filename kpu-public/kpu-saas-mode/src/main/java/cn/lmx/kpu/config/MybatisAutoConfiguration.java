package cn.lmx.kpu.config;


import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.datasource.BaseMybatisConfiguration;
import cn.lmx.basic.database.mybatis.auth.DataScopeInnerInterceptor;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.database.properties.MultiTenantType;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.authority.service.auth.UserService;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class MybatisAutoConfiguration extends BaseMybatisConfiguration {

    public MybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    /**
     * 数据权限插件
     *
     * @return 数据权限插件
     */
    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();

//        if (StrUtil.equalsAny(databaseProperties.getMultiTenantType().name(),
//                MultiTenantType.SCHEMA.name(), MultiTenantType.SCHEMA_COLUMN.name())) {
//            ArgumentAssert.notNull(databaseProperties.getDbType(), "SCHEMA 模式请在mysql.yml、oracle.yml、sqlserver.yml中配置: {}.dbType", DatabaseProperties.PREFIX);
//
//            // SCHEMA 动态表名插件
//            SchemaInterceptor schemaInterceptor = new SchemaInterceptor(databaseProperties.getTenantDatabasePrefix(), databaseProperties.getOwner(), databaseProperties.getDbType());
//            list.add(schemaInterceptor);
//        }
        if (StrUtil.equalsAny(databaseProperties.getMultiTenantType().name(),
                MultiTenantType.COLUMN.name(), MultiTenantType.SCHEMA_COLUMN.name(), MultiTenantType.DATASOURCE_COLUMN.name())) {
            log.info("检测配置了:{}模式，已加载，column 部分插件", databaseProperties.getMultiTenantType());
            // COLUMN 模式 多租户插件
            TenantLineInnerInterceptor tli = new TenantLineInnerInterceptor();
            tli.setTenantLineHandler(new TenantLineHandler() {
                @Override
                public String getTenantIdColumn() {
                    return databaseProperties.getTenantIdColumn();
                }

                @Override
                public boolean ignoreTable(String tableName) {
                    return databaseProperties.getIgnoreTables() != null && databaseProperties.getIgnoreTables().contains(tableName);
                }

                @Override
                public Expression getTenantId() {
                    return MultiTenantType.COLUMN.eq(databaseProperties.getMultiTenantType()) ?
                            new StringValue(ContextUtil.getTenant()) :
                            new StringValue(ContextUtil.getSubTenant());
                }
            });
            list.add(tli);
        }

        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(userId -> SpringUtils.getBean(UserService.class).getDataScopeById(userId)));
        }
        return list;
    }
}
