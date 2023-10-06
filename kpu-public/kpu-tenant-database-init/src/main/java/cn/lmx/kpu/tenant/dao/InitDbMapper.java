package cn.lmx.kpu.tenant.dao;

import cn.lmx.kpu.tenant.dto.DatasourceConfigBO;
import cn.lmx.kpu.tenant.dto.TenantBo;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 初始化数据库DAO
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface InitDbMapper {
    /**
     * 创建数据库
     *
     * @param database 数据库
     * @return 创建数量
     */
    int createDatabase(@Param("database") String database);


    /**
     * 删除数据库
     *
     * @param database 数据库
     * @return 删除数量
     */
    int dropDatabase(@Param("database") String database);

    List<DatasourceConfigBO> selectDataSourceConfig(@Param("statusList") List<String> statusList, @Param("connectType") String connectType);

    List<Long> selectTenantCodeList(@Param("statusList") List<String> statusList, @Param("connectType") String connectType);

    TenantBo getTenantBy(@Param("tenantId") Long tenantId);

    List<DatasourceConfigBO> selectDataSourceConfigByTenantId(@Param("tenantId") Long tenantId);
}
