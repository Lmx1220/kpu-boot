package cn.lmx.kpu.system.mapper.system;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.system.entity.system.DefParameter;

/**
 * <p>
 * Mapper 接口
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefParameterMapper extends SuperMapper<DefParameter> {

}
