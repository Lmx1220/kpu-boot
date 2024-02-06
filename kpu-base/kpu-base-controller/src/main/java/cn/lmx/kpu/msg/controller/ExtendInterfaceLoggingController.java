package cn.lmx.kpu.msg.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLogging;
import cn.lmx.kpu.msg.service.ExtendInterfaceLoggingService;
import cn.lmx.kpu.msg.vo.query.ExtendInterfaceLoggingPageQuery;
import cn.lmx.kpu.msg.vo.result.ExtendInterfaceLoggingResultVO;
import cn.lmx.kpu.msg.vo.save.ExtendInterfaceLoggingSaveVO;
import cn.lmx.kpu.msg.vo.update.ExtendInterfaceLoggingUpdateVO;

/**
 * <p>
 * 前端控制器
 * 接口执行日志记录
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendInterfaceLogging")
@Tag(name = "接口执行日志记录")
public class ExtendInterfaceLoggingController extends SuperController<ExtendInterfaceLoggingService, Long, ExtendInterfaceLogging, ExtendInterfaceLoggingSaveVO,
        ExtendInterfaceLoggingUpdateVO, ExtendInterfaceLoggingPageQuery, ExtendInterfaceLoggingResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


