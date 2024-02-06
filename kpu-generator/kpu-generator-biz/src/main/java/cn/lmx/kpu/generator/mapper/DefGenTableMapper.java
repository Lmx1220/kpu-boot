package cn.lmx.kpu.generator.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.generator.entity.DefGenTable;

/**
 * <p>
 * Mapper 接口
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefGenTableMapper extends SuperMapper<DefGenTable> {

}
