package cn.lmx.kpu.base.manager.system.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.base.entity.system.BaseOperationLog;
import cn.lmx.kpu.base.manager.system.BaseOperationLogManager;
import cn.lmx.kpu.base.mapper.system.BaseOperationLogExtMapper;
import cn.lmx.kpu.base.mapper.system.BaseOperationLogMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 操作日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseOperationLogManagerImpl extends SuperManagerImpl<BaseOperationLogMapper, BaseOperationLog> implements BaseOperationLogManager {
    private final BaseOperationLogExtMapper baseOperationLogExtMapper;

    @Override
    public Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        List<Long> idList = Collections.emptyList();
        if (clearBeforeNum != null) {
            Page<BaseOperationLog> page = super.page(new Page<>(0, clearBeforeNum), Wraps.<BaseOperationLog>lbQ().select(BaseOperationLog::getId).orderByDesc(BaseOperationLog::getCreatedTime));
            idList = page.getRecords().stream().map(BaseOperationLog::getId).toList();
        }
        baseOperationLogExtMapper.clearLog(clearBeforeTime, idList);
        return baseMapper.clearLog(clearBeforeTime, idList);
    }

}
