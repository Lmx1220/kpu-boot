package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.MsgRecipientManager;
import cn.lmx.kpu.msg.mapper.MsgRecipientMapper;

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

}


