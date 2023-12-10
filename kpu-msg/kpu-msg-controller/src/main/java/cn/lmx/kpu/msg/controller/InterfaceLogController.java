package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.InterfaceLogService;
import cn.lmx.kpu.msg.entity.InterfaceLog;
import cn.lmx.kpu.msg.vo.save.InterfaceLogSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLogUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLogResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLogPageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/interfaceLog")
@Api(value = "InterfaceLog", tags = "接口执行日志")
public class InterfaceLogController extends SuperController<InterfaceLogService, Long, InterfaceLog, InterfaceLogSaveVO,
    InterfaceLogUpdateVO, InterfaceLogPageQuery, InterfaceLogResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


