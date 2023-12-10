package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.InterfaceManager;
import cn.lmx.kpu.msg.mapper.InterfaceMapper;

/**
 * <p>
 * 通用业务实现类
 * 接口
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class InterfaceManagerImpl extends SuperManagerImpl<InterfaceMapper, Interface> implements InterfaceManager {

}


