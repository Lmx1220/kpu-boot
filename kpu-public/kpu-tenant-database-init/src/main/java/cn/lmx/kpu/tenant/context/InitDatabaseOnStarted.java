package cn.lmx.kpu.tenant.context;

import cn.lmx.kpu.tenant.service.DatasourceInitDataSourceService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  14:58
 */
@AllArgsConstructor
public class InitDatabaseOnStarted implements ApplicationListener<ApplicationStartedEvent> {

    private final DatasourceInitDataSourceService datasourceInitDataSourceService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        datasourceInitDataSourceService.initDataSource();
    }

}
