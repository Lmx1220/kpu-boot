package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgTemplate;

import cn.lmx.kpu.msg.vo.save.MsgSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgPublishVO;
import cn.lmx.kpu.msg.vo.update.MsgSendVO;
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
    /**
     * 发送消息
     *
     * @param data        消息
     * @param msgTemplate 消息模版
     * @param sysUser     当前用户
     * @return 是否执行
     * @author lmx
     * @date 2023-12-10 18:14:10
     */

    Boolean send(MsgSendVO data, MsgTemplate msgTemplate, SysUser sysUser);

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
     * @date 2023-12-10 18:14:10
     */
    Boolean publish(MsgPublishVO data, SysUser sysUser);

    /**
     * 查询消息详情
     *
     * @param id
     * @return
     */
    MsgResultVO getResultById(Long id);
}


