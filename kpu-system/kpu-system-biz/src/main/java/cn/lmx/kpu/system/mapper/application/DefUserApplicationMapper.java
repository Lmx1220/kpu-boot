package cn.lmx.kpu.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.system.entity.application.DefUserApplication;

/**
 * <p>
 * Mapper 接口
 * 用户的默认应用
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefUserApplicationMapper extends SuperMapper<DefUserApplication> {

}
