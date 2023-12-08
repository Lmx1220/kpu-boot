package cn.lmx.kpu.sms.controller;

import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.service.ESmsTemplateService;
import cn.lmx.kpu.sms.vo.query.ESmsTemplatePageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsTemplateResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsTemplateSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsTemplateUpdateVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信模板
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
@RequestMapping("/eSmsTemplate")
@Api(value = "ESmsTemplate", tags = "短信模板")
public class ESmsTemplateController extends SuperController<ESmsTemplateService, Long, ESmsTemplate, ESmsTemplateSaveVO,
        ESmsTemplateUpdateVO, ESmsTemplatePageQuery, ESmsTemplateResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


