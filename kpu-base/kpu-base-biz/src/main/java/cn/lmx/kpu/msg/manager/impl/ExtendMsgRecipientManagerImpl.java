package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.msg.entity.ExtendMsgRecipient;
import cn.lmx.kpu.msg.manager.ExtendMsgRecipientManager;
import cn.lmx.kpu.msg.mapper.ExtendMsgRecipientMapper;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
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
public class ExtendMsgRecipientManagerImpl extends SuperManagerImpl<ExtendMsgRecipientMapper, ExtendMsgRecipient> implements ExtendMsgRecipientManager {
    @Override
    public List<ExtendMsgRecipient> listByMsgId(Long id) {
        return list(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, id));
    }
}


