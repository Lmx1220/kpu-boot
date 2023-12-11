package cn.lmx.kpu.msg.manager.impl;

import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.MsgRecipientManager;
import cn.lmx.kpu.msg.mapper.MsgRecipientMapper;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 21:43:28
 * @create [2023-12-10 21:43:28] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MsgRecipientManagerImpl extends SuperManagerImpl<MsgRecipientMapper, MsgRecipient> implements MsgRecipientManager {

    @Override
    public List<MsgRecipient> listByMsgId(Long id) {
        return getBaseMapper().selectList(new LbqWrapper<MsgRecipient>().eq(MsgRecipient::getMsgId, id));
    }
}


