package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.msg.entity.Task;
import cn.lmx.kpu.msg.service.TaskService;
import cn.lmx.kpu.msg.vo.query.TaskPageQuery;
import cn.lmx.kpu.msg.vo.result.TaskResultVO;
import cn.lmx.kpu.msg.vo.save.TaskSaveVO;
import cn.lmx.kpu.msg.vo.update.TaskUpdateVO;
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
@RequestMapping("/task")
@Api(value = "Task", tags = "发送任务")
public class TaskController extends SuperController<TaskService, Long, Task, TaskSaveVO,
        TaskUpdateVO, TaskPageQuery, TaskResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


