package cn.lmx.kpu.msg.strategy.domain;

import cn.lmx.kpu.file.entity.Appendix;
import cn.lmx.kpu.msg.entity.ExtendMsg;
import cn.lmx.kpu.msg.entity.ExtendMsgRecipient;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/11/19  18:09
 */
// 入参是调用脚本时自动传入的参数
public class MsgParam {
    /** 消息内容 */
    private ExtendMsg extendMsg;
    /** 消息接收人 */
    private List<ExtendMsgRecipient> recipientList;
    /** 采用的消息模板 */
    private MsgTemplate extendMsgTemplate;
    /** 接口需要使用的动态参数 */
    private Map<String, Object> propertyParams;
    /** 消息的附件 */
    private List<Appendix> list;
}
