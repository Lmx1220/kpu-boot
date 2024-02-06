package cn.lmx.kpu.msg.mapper;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.msg.entity.InterfaceLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Repository
public interface InterfaceLogMapper extends SuperMapper<InterfaceLog> {

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


