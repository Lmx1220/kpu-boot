package cn.lmx.kpu.system.manager.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.system.entity.application.DefUserApplication;
import cn.lmx.kpu.system.manager.application.DefUserApplicationManager;
import cn.lmx.kpu.system.mapper.application.DefUserApplicationMapper;

/**
 * <p>
 * 通用业务实现类
 * 用户的默认应用
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefUserApplicationManagerImpl extends SuperManagerImpl<DefUserApplicationMapper, DefUserApplication> implements DefUserApplicationManager {
}
