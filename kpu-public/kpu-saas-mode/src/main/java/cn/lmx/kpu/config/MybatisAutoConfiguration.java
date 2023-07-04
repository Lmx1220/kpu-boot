package cn.lmx.kpu.config;


import cn.lmx.basic.database.datasource.BaseMybatisConfiguration;
import cn.lmx.basic.database.mybatis.auth.DataScopeInnerInterceptor;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.authority.service.auth.UserService;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
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
        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(userId -> SpringUtils.getBean(UserService.class).getDataScopeById(userId)));
        }
        return list;
    }
}
