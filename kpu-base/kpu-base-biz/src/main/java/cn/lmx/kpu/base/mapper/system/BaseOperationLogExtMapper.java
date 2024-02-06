package cn.lmx.kpu.base.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.base.entity.system.BaseOperationLogExt;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 操作日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface BaseOperationLogExtMapper extends SuperMapper<BaseOperationLogExt> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param idList          待删除
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("idList") List<Long> idList);
}
