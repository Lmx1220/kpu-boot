package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.InterfacePropertyService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.InterfacePropertyManager;
import cn.lmx.kpu.msg.entity.InterfaceProperty;
import cn.lmx.kpu.msg.vo.save.InterfacePropertySaveVO;
import cn.lmx.kpu.msg.vo.update.InterfacePropertyUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfacePropertyResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePropertyPageQuery;

import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 接口属性
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
public class InterfacePropertyServiceImpl extends SuperServiceImpl<InterfacePropertyManager, Long, InterfaceProperty, InterfacePropertySaveVO,
    InterfacePropertyUpdateVO, InterfacePropertyPageQuery, InterfacePropertyResultVO> implements InterfacePropertyService {


    @Override
    public Map<String, Object> listByInterfaceId(Long id) {
        return getSuperManager().listByInterfaceId(id);
    }
}


