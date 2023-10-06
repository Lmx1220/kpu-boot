package cn.lmx.kpu.config.datasource;

import cn.lmx.basic.database.plugins.TenantLineAnnotationRegister;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.kpu.tenant.context.InitDatabaseOnStarted;
import cn.lmx.kpu.tenant.dynamic.processor.DsThreadProcessor;
import cn.lmx.kpu.tenant.service.DatasourceInitDataSourceService;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  14:45
 */
@Configuration
public class DatasourceColumnAutoConfiguration {
    public DatasourceColumnAutoConfiguration() {
        System.out.println("DatasourceColumnAutoConfiguration");
    }

    /**
     * 项目启动时，初始化数据源
     */
    @Bean
    public InitDatabaseOnStarted getInitDatabaseOnStarted(DatasourceInitDataSourceService initSystemContext) {
        return new InitDatabaseOnStarted(initSystemContext);
    }

    @Bean
    @ConditionalOnMissingBean
    public DsProcessor dsProcessor() {
        DsThreadProcessor threadProcessor = new DsThreadProcessor();
        DsSessionProcessor sessionProcessor = new DsSessionProcessor();
        DsSpelExpressionProcessor dsSpelExpressionProcessor = new DsSpelExpressionProcessor();
        threadProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(dsSpelExpressionProcessor);
        return threadProcessor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE_COLUMN")
    public TenantLineAnnotationRegister getTenantLineAnnotationRegister() {
        return new TenantLineAnnotationRegister();
    }
}

