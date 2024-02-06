package cn.lmx.kpu.file.mapper;

import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.file.entity.File;

/**
 * <p>
 * Mapper 接口
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [初始创建]
 */
@Repository
public interface FileMapper extends SuperMapper<File> {

}
