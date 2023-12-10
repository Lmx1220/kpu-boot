package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.MsgTemplateService;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.vo.save.MsgTemplateSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgTemplateUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgTemplateResultVO;
import cn.lmx.kpu.msg.vo.query.MsgTemplatePageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 消息模板
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
@RequestMapping("/msgTemplate")
@Api(value = "MsgTemplate", tags = "消息模板")
public class MsgTemplateController extends SuperController<MsgTemplateService, Long, MsgTemplate, MsgTemplateSaveVO,
    MsgTemplateUpdateVO, MsgTemplatePageQuery, MsgTemplateResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


