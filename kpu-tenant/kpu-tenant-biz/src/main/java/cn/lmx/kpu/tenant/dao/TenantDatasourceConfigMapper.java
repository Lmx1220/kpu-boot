package cn.lmx.kpu.tenant.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.tenant.entity.TenantDatasourceConfig;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;

/**
 * 租户数据源关系 Mapper
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantDatasourceConfigMapper extends SuperMapper<TenantDatasourceConfig> {
}
