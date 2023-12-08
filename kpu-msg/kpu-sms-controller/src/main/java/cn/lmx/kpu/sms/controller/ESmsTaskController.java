package cn.lmx.kpu.sms.controller;

import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.sms.entity.ESmsTask;
import cn.lmx.kpu.sms.service.ESmsTaskService;
import cn.lmx.kpu.sms.vo.query.ESmsTaskPageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsTaskResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsTaskSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsTaskUpdateVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 * @create [2023-11-14 11:08:01] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/eSmsTask")
@Api(value = "ESmsTask", tags = "发送任务")
public class ESmsTaskController extends SuperController<ESmsTaskService, Long, ESmsTask, ESmsTaskSaveVO,
        ESmsTaskUpdateVO, ESmsTaskPageQuery, ESmsTaskResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


