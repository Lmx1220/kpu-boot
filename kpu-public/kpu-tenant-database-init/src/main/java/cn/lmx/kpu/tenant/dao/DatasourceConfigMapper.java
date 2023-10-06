package cn.lmx.kpu.tenant.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DatasourceConfigMapper extends SuperMapper<DatasourceConfig> {

}
