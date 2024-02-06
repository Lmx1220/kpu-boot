package cn.lmx.kpu.msg.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;

import cn.lmx.kpu.msg.entity.ExtendMsgRecipient;
import cn.lmx.kpu.msg.manager.ExtendMsgRecipientManager;
import cn.lmx.kpu.msg.service.ExtendMsgRecipientService;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendMsgRecipientServiceImpl extends SuperServiceImpl<ExtendMsgRecipientManager, Long, ExtendMsgRecipient> implements ExtendMsgRecipientService {
    @Override
    public List<ExtendMsgRecipient> listByMsgId(Long id) {
        return superManager.listByMsgId(id);
    }

}


