package cn.lmx.kpu.msg.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLog;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Repository
public interface ExtendInterfaceLogMapper extends SuperMapper<ExtendInterfaceLog> {
    /**
     * 递增成功次数
     *
     * @param id  日志ID
     * @param now 当前时间
     * @return
     */
    int incrSuccessCount(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 递增失败次数
     *
     * @param id  日志ID
     * @param now 当前时间
     * @return
     */
    int incrFailCount(@Param("id") Long id, @Param("now") LocalDateTime now);
}


