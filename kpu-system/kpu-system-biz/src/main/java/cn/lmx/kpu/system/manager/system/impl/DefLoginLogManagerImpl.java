package cn.lmx.kpu.system.manager.system.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.system.entity.system.DefLoginLog;
import cn.lmx.kpu.system.manager.system.DefLoginLogManager;
import cn.lmx.kpu.system.mapper.system.DefLoginLogMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 登录日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefLoginLogManagerImpl extends SuperManagerImpl<DefLoginLogMapper, DefLoginLog> implements DefLoginLogManager {
    @Override
    public Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        List<Long> idList = Collections.emptyList();
        if (clearBeforeNum != null) {
            Page<DefLoginLog> page = super.page(new Page<>(0, clearBeforeNum), Wraps.<DefLoginLog>lbQ().select(DefLoginLog::getId).orderByDesc(DefLoginLog::getCreatedTime));
            idList = page.getRecords().stream().map(DefLoginLog::getId).toList();
        }
        return baseMapper.clearLog(clearBeforeTime, idList);
    }
}
