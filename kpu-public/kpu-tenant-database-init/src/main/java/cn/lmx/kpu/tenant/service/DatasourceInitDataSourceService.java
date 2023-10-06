package cn.lmx.kpu.tenant.service;

import cn.lmx.kpu.common.constant.DsConstants;
import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  15:10
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class DatasourceInitDataSourceService {

    private final DataSourceService dataSourceService;

    @DS(DsConstants.DEFAULTS)
    public boolean initDataSource() {
        dataSourceService.loadCustomDataSource();
        dataSourceService.loadSystemDataSource();
        log.debug("初始化数据源成功");
        return true;
    }
}
