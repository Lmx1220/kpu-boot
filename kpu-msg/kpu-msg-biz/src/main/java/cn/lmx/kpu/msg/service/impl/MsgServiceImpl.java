package cn.lmx.kpu.msg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.base.R;
import cn.lmx.basic.context.ContextConstants;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.common.api.JobApi;
import cn.lmx.kpu.common.constant.JobConstant;
import cn.lmx.kpu.common.dto.XxlJobInfoVO;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import cn.lmx.kpu.msg.enumeration.SourceType;
import cn.lmx.kpu.msg.enumeration.TaskStatus;
import cn.lmx.kpu.msg.event.MsgSendEvent;
import cn.lmx.kpu.msg.event.model.MsgEventVO;
import cn.lmx.kpu.msg.manager.MsgRecipientManager;
import cn.lmx.kpu.msg.manager.NoticeManager;
import cn.lmx.kpu.msg.vo.update.MsgPublishVO;
import cn.lmx.kpu.msg.vo.update.MsgSendVO;
import cn.lmx.kpu.msg.ws.WebSocketSubject;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.MsgService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.MsgManager;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.vo.save.MsgSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgResultVO;
import cn.lmx.kpu.msg.vo.query.MsgPageQuery;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 消息表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MsgServiceImpl extends SuperServiceImpl<MsgManager, Long, Msg, MsgSaveVO,
    MsgUpdateVO, MsgPageQuery, MsgResultVO> implements MsgService {

    private final JobApi jobApi;
    private final MsgRecipientManager recipientManager;
    private final NoticeManager noticeManager;
    @Override
    public MsgResultVO getResultById(Long id) {
        Msg msg = superManager.getById(id);
        MsgResultVO result = BeanUtil.toBean(msg, MsgResultVO.class);
        List<MsgRecipient> list = recipientManager.listByMsgId(id);
        result.setRecipientList(list.stream().map(MsgRecipient::getRecipient).collect(Collectors.toList()));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean publish(MsgPublishVO data, SysUser sysUser) {
        Msg msg = BeanUtil.toBean(data, Msg.class);
        msg.setType(MsgTemplateTypeEnum.NOTICE.getCode());
        msg.setChannel(SourceType.APP);

//        msg.setCreatedOrgId(sysUser.getEmployee() != null ? sysUser.getEmployee().getLastDeptId() : null);
        if (data != null && data.getDraft() != null && data.getDraft()) {
            msg.setStatus(TaskStatus.DRAFT);
        } else {
            msg.setStatus(TaskStatus.WAITING);
        }
        if (msg.getId() == null) {
            superManager.save(msg);
        } else {
            superManager.updateById(msg);
            recipientManager.remove(Wraps.<MsgRecipient>lbQ().eq(MsgRecipient::getMsgId, msg.getId()));
        }
        List<MsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            MsgRecipient recipient = new MsgRecipient();
            recipient.setMsgId(msg.getId());
            recipient.setRecipient(item);
            return recipient;
        }).collect(Collectors.toList());
        recipientManager.saveBatch(recipientList);

        if (data.getSendTime() == null) {
            List<Notice> noticeList = data.getRecipientList().stream().map((item) -> {
                Notice notice = new Notice();
                BeanUtil.copyProperties(msg, notice);
                notice.setId(null);
                notice.setMsgId(msg.getId());
                notice.setRecipientId(Long.valueOf(item));
                notice.setIsHandle(false);
                notice.setIsRead(false);
                notice.setHandleTime(null);
                notice.setReadTime(null);
                notice.setAutoRead(true);
                return notice;
            }).collect(Collectors.toList());
            noticeManager.saveBatch(noticeList);

            data.getRecipientList().forEach(employeeId -> {
                WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
                // 通知客户端 接收消息
                subject.notify("1", null);
            });

            msg.setStatus(TaskStatus.SUCCESS);
            superManager.updateById(msg);
        } else {
            // 务必启动 lamp-job-pro 项目，否则调用会失败！
            Map<String, Long> param = MapUtil.builder("msgId", msg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create("lamp-none-executor",
                    "【发送消息】" + msg.getTitle(), msg.getSendTime(), "publishMsg", JsonUtil.toJson(param));
            jobApi.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishNotice(Long msgId) {
        Msg msg = superManager.getById(msgId);
        ArgumentAssert.notNull(msg, "消息不存在");
        List<MsgRecipient> recipientList = recipientManager.listByMsgId(msg.getId());
        ArgumentAssert.notEmpty(recipientList, "消息接收人为空");

        List<Notice> noticeList = recipientList.stream().map((item) -> {
            Notice notice = new Notice();
            BeanUtil.copyProperties(msg, notice);
            notice.setId(null);
            notice.setMsgId(msg.getId());
            notice.setRecipientId(Long.valueOf(item.getRecipient()));
            notice.setIsHandle(false);
            notice.setIsRead(false);
            notice.setHandleTime(null);
            notice.setReadTime(null);
            notice.setAutoRead(true);
            return notice;
        }).collect(Collectors.toList());
        noticeManager.saveBatch(noticeList);

        recipientList.forEach(employeeId -> {
            WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
            // 通知客户端 接收消息
            subject.notify("1", null);
        });

        msg.setStatus(TaskStatus.SUCCESS);
        superManager.updateById(msg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean send(MsgSendVO data, MsgTemplate msgTemplate, SysUser sysUser) {
        Msg msg = BeanUtil.toBean(data, Msg.class);
        msg.setChannel(SourceType.SERVICE);
        msg.setType(msgTemplate.getType());
        msg.setRemindMode(msgTemplate.getRemindMode());
        if (CollUtil.isNotEmpty(data.getParamList())) {
            msg.setParam(JsonUtil.toJson(data.getParamList()));
        }
//        msg.setCreatedOrgId((sysUser != null && sysUser.getEmployee() != null) ? sysUser.getEmployee().getLastDeptId() : null);

        msg.setStatus(TaskStatus.WAITING);
        if (msg.getId() == null) {
            superManager.save(msg);
        } else {
            superManager.updateById(msg);
            recipientManager.remove(Wraps.<MsgRecipient>lbQ().eq(MsgRecipient::getMsgId, msg.getId()));
        }

        List<MsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            MsgRecipient recipient = new MsgRecipient();
            recipient.setMsgId(msg.getId());
            recipient.setRecipient(item.getRecipient());
            recipient.setExt(item.getExt());
            return recipient;
        }).collect(Collectors.toList());
        recipientManager.saveBatch(recipientList);

        //3, 判断是否立即发送
        if (data.getSendTime() == null) {
            MsgEventVO msgEventVO = new MsgEventVO();
            msgEventVO.setMsgId(msg.getId()).copy();
            SpringUtils.publishEvent(new MsgSendEvent(msgEventVO));
        } else {
            // 务必启动 lamp-job-pro 项目，否则调用会失败！
            Map<String, Long> param = MapUtil.builder("msgId", msg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create("lamp-none-executor",
                    "【发送消息】" + msg.getTitle(), msg.getSendTime(), "sendMsg", JsonUtil.toJson(param));
            jobApi.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }

}


