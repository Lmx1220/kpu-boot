package cn.lmx.kpu.msg.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.msg.entity.ExtendInterfaceLog;
import cn.lmx.kpu.msg.service.ExtendInterfaceLogService;
import cn.lmx.kpu.msg.vo.query.ExtendInterfaceLogPageQuery;
import cn.lmx.kpu.msg.vo.result.ExtendInterfaceLogResultVO;
import cn.lmx.kpu.msg.vo.save.ExtendInterfaceLogSaveVO;
import cn.lmx.kpu.msg.vo.update.ExtendInterfaceLogUpdateVO;

/**
 * <p>
 * 前端控制器
 * 接口执行日志
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
@RequestMapping("/extendInterfaceLog")
@Tag(name = "接口执行日志")
public class ExtendInterfaceLogController extends SuperController<ExtendInterfaceLogService, Long, ExtendInterfaceLog, ExtendInterfaceLogSaveVO,
        ExtendInterfaceLogUpdateVO, ExtendInterfaceLogPageQuery, ExtendInterfaceLogResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


