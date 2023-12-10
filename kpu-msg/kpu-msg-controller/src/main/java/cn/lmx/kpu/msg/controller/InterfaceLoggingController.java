package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.InterfaceLoggingService;
import cn.lmx.kpu.msg.entity.InterfaceLogging;
import cn.lmx.kpu.msg.vo.save.InterfaceLoggingSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLoggingUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLoggingResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLoggingPageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口执行日志记录
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
@RequestMapping("/interfaceLogging")
@Api(value = "InterfaceLogging", tags = "接口执行日志记录")
public class InterfaceLoggingController extends SuperController<InterfaceLoggingService, Long, InterfaceLogging, InterfaceLoggingSaveVO,
    InterfaceLoggingUpdateVO, InterfaceLoggingPageQuery, InterfaceLoggingResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


