package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.NoticeService;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.kpu.msg.vo.save.NoticeSaveVO;
import cn.lmx.kpu.msg.vo.update.NoticeUpdateVO;
import cn.lmx.kpu.msg.vo.result.NoticeResultVO;
import cn.lmx.kpu.msg.vo.query.NoticePageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 通知表
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
@RequestMapping("/notice")
@Api(value = "Notice", tags = "通知表")
public class NoticeController extends SuperController<NoticeService, Long, Notice, NoticeSaveVO,
    NoticeUpdateVO, NoticePageQuery, NoticeResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


