package cn.lmx.kpu.msg.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.msg.entity.DefInterfaceProperty;

/**
 * <p>
 * Mapper 接口
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefInterfacePropertyMapper extends SuperMapper<DefInterfaceProperty> {

}


