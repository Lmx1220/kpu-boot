package cn.lmx.kpu.msg.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.msg.entity.ExtendMsgRecipient;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
public interface ExtendMsgRecipientManager extends SuperManager<ExtendMsgRecipient> {

    /**
     * 根据消息ID查询接收人
     *
     * @param id
     * @return
     */
    List<ExtendMsgRecipient> listByMsgId(Long id);
}


