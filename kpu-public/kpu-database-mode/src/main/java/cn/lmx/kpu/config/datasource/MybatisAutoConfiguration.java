package cn.lmx.kpu.config.datasource;


import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.datasource.BaseMybatisConfiguration;
import cn.lmx.basic.database.plugins.KpuTenantLineInnerInterceptor;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.database.properties.MultiTenantType;
import cn.lmx.kpu.tenant.auth.DataScopeInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cn.lmx.kpu.common.constant.BizConstant.BUSINESS_PACKAGE;
import static cn.lmx.kpu.common.constant.BizConstant.UTIL_PACKAGE;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Configuration
@MapperScan(
        basePackages = {BUSINESS_PACKAGE, UTIL_PACKAGE},
        annotationClass = Repository.class)
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
        if (MultiTenantType.DATASOURCE_COLUMN.eq(databaseProperties.getMultiTenantType())) {
            log.info("检查到配置了:{}模式，已加载 column 部分插件", databaseProperties.getMultiTenantType());
            // COLUMN 模式 多租户插件
            KpuTenantLineInnerInterceptor tli = new KpuTenantLineInnerInterceptor();
            tli.setTenantLineHandler(new TenantLineHandler() {
                @Override
                public String getTenantIdColumn() {
                    return databaseProperties.getTenantIdColumn();
                }

                @Override
                public boolean ignoreTable(String tableName) {
                    // 这里可以自己在ContextUtil 中加入一个 isIgnore、setIgnore、clearIgnore 方法，动态控制是否需要排除
//                    if (ContextUtil.isIgnore()) {
//                    ContextUtil.clearIgnore();
//                        return true;
//                    }

                    boolean ignoreTable = databaseProperties.getIgnoreTable() != null && databaseProperties.getIgnoreTable().contains(tableName);

                    boolean ignoreTablePrefix = databaseProperties.getIgnoreTablePrefix() != null &&
                            databaseProperties.getIgnoreTablePrefix().stream().anyMatch(tableName::startsWith);
                    return ignoreTable || ignoreTablePrefix;
                }

                @Override
                public Expression getTenantId() {
                    return new LongValue(ContextUtil.getCurrentCompanyId());
                }
            });

            list.add(tli);
        }

        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(getDataScopeInnerInterceptor());
//
        }
        return list;

    }

    @Bean
    @Order(4)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "isDataScope", havingValue = "true")
    public DataScopeInnerInterceptor getDataScopeInnerInterceptor() {
        return new DataScopeInnerInterceptor();
    }
}
