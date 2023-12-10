package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.vo.save.MsgRecipientSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgRecipientUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgRecipientResultVO;
import cn.lmx.kpu.msg.vo.query.MsgRecipientPageQuery;


/**
 * <p>
 * 业务接口
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 21:43:28
 * @create [2023-12-10 21:43:28] [lmx] [代码生成器生成]
 */
public interface MsgRecipientService extends SuperService<Long, MsgRecipient, MsgRecipientSaveVO,
    MsgRecipientUpdateVO, MsgRecipientPageQuery, MsgRecipientResultVO> {

}


