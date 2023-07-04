package cn.lmx.kpu.tenant.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.tenant.entity.Tenant;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 企业
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantMapper extends SuperMapper<Tenant> {

    /**
     * 根据租户编码查询
     *
     * @param code 租户编码
     * @return 租户
     */
    Tenant getByCode(@Param("code") String code);
}
