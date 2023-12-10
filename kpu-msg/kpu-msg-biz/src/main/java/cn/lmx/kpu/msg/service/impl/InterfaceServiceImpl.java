package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.InterfaceService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.InterfaceManager;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.kpu.msg.vo.save.InterfaceSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePageQuery;

/**
 * <p>
 * 业务实现类
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
@Transactional(readOnly = true)
public class InterfaceServiceImpl extends SuperServiceImpl<InterfaceManager, Long, Interface, InterfaceSaveVO,
    InterfaceUpdateVO, InterfacePageQuery, InterfaceResultVO> implements InterfaceService {


}


