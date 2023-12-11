package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.strategy.domain.MsgPublishVO;
import cn.lmx.kpu.msg.strategy.domain.MsgSendVO;
import cn.lmx.kpu.msg.vo.save.MsgSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgResultVO;
import cn.lmx.kpu.msg.vo.query.MsgPageQuery;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 业务接口
 * 消息表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface MsgService extends SuperService<Long, Msg, MsgSaveVO,
    MsgUpdateVO, MsgPageQuery, MsgResultVO> {

    Boolean send(MsgSendVO data, MsgTemplate msgTemplate, SysUser sysUser);

    MsgResultVO getResulByI(Long id);

    Boolean publish(MsgPublishVO data, SysUser sysUser);

    @Transactional(rollbackFor = Exception.class)
    Boolean publishNotice(Long msgId);
}


