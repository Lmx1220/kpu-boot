package cn.lmx.kpu.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.system.entity.application.DefResourceApi;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源接口
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefResourceApiMapper extends SuperMapper<DefResourceApi> {
    /**
     * 查询指定租户下指定应用和指定资源类型的 接口
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<cn.lmx.kpu.system.entity.application.DefResourceApi>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<DefResourceApi> findResourceApi(@Param("applicationIdList") List<Long> applicationIdList,
                                         @Param("resourceTypes") Collection<String> resourceTypes);
}
