package cn.lmx.kpu.sms.controller;

import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.sms.entity.ESmsSendStatus;
import cn.lmx.kpu.sms.service.ESmsSendStatusService;
import cn.lmx.kpu.sms.vo.query.ESmsSendStatusPageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsSendStatusResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsSendStatusSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsSendStatusUpdateVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/eSmsSendStatus")
@Api(value = "ESmsSendStatus", tags = "短信发送状态")
public class ESmsSendStatusController extends SuperController<ESmsSendStatusService, Long, ESmsSendStatus, ESmsSendStatusSaveVO,
        ESmsSendStatusUpdateVO, ESmsSendStatusPageQuery, ESmsSendStatusResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


