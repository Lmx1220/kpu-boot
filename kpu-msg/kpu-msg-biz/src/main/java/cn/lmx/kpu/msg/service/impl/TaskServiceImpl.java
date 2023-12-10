package cn.lmx.kpu.msg.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.context.ContextConstants;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.model.Kv;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.api.JobApi;
import cn.lmx.kpu.common.constant.JobConstant;
import cn.lmx.kpu.common.dto.XxlJobInfoVO;
import cn.lmx.kpu.msg.entity.SendStatus;
import cn.lmx.kpu.msg.entity.Task;
import cn.lmx.kpu.msg.entity.Template;
import cn.lmx.kpu.msg.enumeration.SendStatus;
import cn.lmx.kpu.msg.enumeration.SourceType;
import cn.lmx.kpu.msg.enumeration.TaskStatus;
import cn.lmx.kpu.msg.manager.TaskManager;
import cn.lmx.kpu.msg.service.SendStatusService;
import cn.lmx.kpu.msg.service.TaskService;
import cn.lmx.kpu.msg.service.TemplateService;
import cn.lmx.kpu.msg.strategy.MsgContext;
import cn.lmx.kpu.msg.vo.query.TaskPageQuery;
import cn.lmx.kpu.msg.vo.result.TaskResultVO;
import cn.lmx.kpu.msg.vo.save.TaskSaveVO;
import cn.lmx.kpu.msg.vo.update.TaskUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.lmx.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;

/**
 * <p>
 * 业务实现类
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 * @create [2023-11-14 11:08:01] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl extends SuperServiceImpl<TaskManager, Long, Task, TaskSaveVO,
        TaskUpdateVO, TaskPageQuery, TaskResultVO> implements TaskService {
    private final JobApi jobApi;
    private final MsgContext smsContext;
    private final TemplateService eSmsTemplateService;
    private final SendStatusService eSmsSendStatusService;
    private static String processTemplate(String template, String regex, List<Kv> params) {
        log.info("regex={}, template={}, params={}", regex, template, params);
        if (StrUtil.isEmpty(template)) {
            return StrPool.EMPTY;
        }
        if (CollUtil.isEmpty(params)) {
            return StrPool.EMPTY;
        }

        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(regex).matcher(template);
        int index = 0;
        while (m.find()) {
            String value = "";
            if (index + 1 <= params.size()) {
                Kv kv = params.get(index);
                value = kv.getValue();
            }
            value = value == null ? "" : value;
            if (value.contains("$")) {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
            m.appendReplacement(sb, value);
            index++;
        }
        m.appendTail(sb);
        if (index < params.size()) {
            throw BizException.wrap("模板中识别出的参数个数: {} 少于传入的参数个数: {}", params.size(), index);
        } else if (index > params.size()) {
            throw BizException.wrap("模板中识别出的参数个数: {} 多于传入的参数个数: {}", params.size(), index);
        }
        return sb.toString();
    }
    /**
     * 验证数据，并初始化数据
     */
    public void validAndInit(TaskSaveVO smsTask) {
        Template template;

        template = eSmsTemplateService.getById(smsTask.getTemplateId());
        ArgumentAssert.notNull(template, "请选择正确的短信模板");


        //1，验证必要参数
        ArgumentAssert.notEmpty(smsTask.getTelNum(), "请填写短信接收人");

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (smsTask.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(smsTask.getSendTime());
            ArgumentAssert.isTrue(flag, "定时发送时间至少在当前时间的5分钟之后");
        }

        if (StrUtil.isEmpty(smsTask.getContent())) {
            smsTask.setContent(processTemplate(template.getContent(), template.getProviderType().getRegex(), smsTask.getTemplateParam()));
        } else if (smsTask.getContent().length() > 500) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过500字");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task saveTask(TaskSaveVO data) {
        if (data.getSourceType() == null) {
            data.setSourceType(SourceType.APP);
        }
        validAndInit(data);

        Task smsTask = BeanPlusUtil.toBean(data, Task.class);
        smsTask.setTemplateParams(JsonUtil.toJson(data.getTemplateParam()));
        send(smsTask, (task) -> {
            this.getSuperManager().save(task);
            List<SendStatus> list = data.getTelNum().stream().map(telNum -> {
                SendStatus sss = new SendStatus();
                sss.setTaskId(task.getId());
                sss.setSendStatus(SendStatus.WAITING);
                sss.setTelNum(telNum);
                return sss;
            }).collect(Collectors.toList());
            eSmsSendStatusService.saveBatch(list);
            return true;
        });
        return smsTask;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task updateById(TaskUpdateVO data) {
        TaskSaveVO validDTO = BeanPlusUtil.toBean(data, TaskSaveVO.class);
        validAndInit(validDTO);

        Task smsTask = BeanPlusUtil.toBean(validDTO, Task.class);
        smsTask.setId(data.getId());
        smsTask.setSourceType(SourceType.APP);
        smsTask.setTemplateParams(data.getTemplateParam().toString());

        send(smsTask, (task) -> {
            this.getSuperManager().updateById(task);
            if (task.getSendTime() == null) {
                this.getSuperManager().update(Wraps.<Task>lbU()
                        .set(Task::getSendTime, null)
                        .eq(Task::getId, task.getId()));
            }
            eSmsSendStatusService.remove(Wraps.<SendStatus>lbQ().eq(SendStatus::getTaskId, task.getId()));
            List<SendStatus> list = data.getTelNum().stream().map(telNum -> {
                SendStatus sss = new SendStatus();
                sss.setTaskId(task.getId());
                sss.setSendStatus(SendStatus.WAITING);
                sss.setTelNum(telNum);
                return sss;
            }).collect(Collectors.toList());
            eSmsSendStatusService.saveBatch(list);
            return true;
        });
        return smsTask;
    }

    /**
     * 具体的短信任务保存操作
     */
    private Task send(Task smsTask, Function<Task, Boolean> function) {
        //1， 初始化默认参数
        smsTask.setStatus(TaskStatus.WAITING);

        //2，保存or修改 短信任务
        if (!function.apply(smsTask)) {
            return smsTask;
        }

        //保存草稿，直接返回
        if (smsTask.getDraft()) {
            return smsTask;
        }

        //3, 判断是否立即发送
        if (smsTask.getSendTime() == null) {
            smsContext.smsSend(smsTask.getId());
        } else {
            JSONObject param = new JSONObject();
            param.set("id", smsTask.getId());
            param.set(ContextConstants.JWT_KEY_TENANT, ContextUtil.getTenant());
            //推送定时任务
            R<String> r = jobApi.addTimingTask(
                    XxlJobInfoVO.create(JobConstant.DEF_EXTEND_JOB_GROUP_NAME,
                            smsTask.getTopic(),
                            smsTask.getSendTime(),
                            JobConstant.SMS_SEND_JOB_HANDLER,
                            param.toString()));
            if (!r.getIsSuccess()) {
                throw BizException.wrap("定时发送失败");
            }
        }
        return smsTask;
    }
}


