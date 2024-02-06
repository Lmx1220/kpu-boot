package cn.lmx.kpu.test.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;

import cn.lmx.kpu.test.entity.DefGenTestSimple;
import cn.lmx.kpu.test.manager.DefGenTestSimpleManager;
import cn.lmx.kpu.test.service.DefGenTestSimpleService;

/**
 * <p>
 * 业务实现类
 * 测试单表
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefGenTestSimpleServiceImpl extends SuperServiceImpl<DefGenTestSimpleManager, Long, DefGenTestSimple> implements DefGenTestSimpleService {

}


