package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.msg.entity.DefMsgTemplate;
import cn.lmx.kpu.msg.entity.ExtendMsg;
import cn.lmx.kpu.msg.vo.result.ExtendMsgResultVO;
import cn.lmx.kpu.msg.vo.update.ExtendMsgPublishVO;
import cn.lmx.kpu.msg.vo.update.ExtendMsgSendVO;


/**
 * <p>
 * 业务接口
 * 消息
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
public interface ExtendMsgService extends SuperService<Long, ExtendMsg> {

    /**
     * 发送消息
     *
     * @param data        消息
     * @param msgTemplate 消息模版
     * @param sysUser     当前用户
     * @return 是否执行
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */

    Boolean send(ExtendMsgSendVO data, DefMsgTemplate msgTemplate, SysUser sysUser);

    /**
     * 定时发布通知
     *
     * @param msgId 消息id
     */
    void publishNotice(Long msgId);

    /**
     * 发布消息
     *
     * @param data
     * @param sysUser
     * @return
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    Boolean publish(ExtendMsgPublishVO data, SysUser sysUser);

    /**
     * 查询消息详情
     *
     * @param id
     * @return
     */
    ExtendMsgResultVO getResultById(Long id);
}


