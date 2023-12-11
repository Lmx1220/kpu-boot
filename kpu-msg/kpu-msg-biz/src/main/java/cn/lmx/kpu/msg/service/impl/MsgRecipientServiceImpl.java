package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.MsgRecipientService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.MsgRecipientManager;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.vo.save.MsgRecipientSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgRecipientUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgRecipientResultVO;
import cn.lmx.kpu.msg.vo.query.MsgRecipientPageQuery;

import java.util.List;

/**
 * <p>
 * 业务实现类
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
@Transactional(readOnly = true)
public class MsgRecipientServiceImpl extends SuperServiceImpl<MsgRecipientManager, Long, MsgRecipient, MsgRecipientSaveVO,
    MsgRecipientUpdateVO, MsgRecipientPageQuery, MsgRecipientResultVO> implements MsgRecipientService {


    @Override
    public List<MsgRecipient> listByMsgId(Long id) {
        return getSuperManager().listByMsgId(id);
    }
}


