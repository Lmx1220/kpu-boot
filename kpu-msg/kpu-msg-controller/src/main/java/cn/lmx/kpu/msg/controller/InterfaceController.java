package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.InterfaceService;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.kpu.msg.vo.save.InterfaceSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口
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
@RequestMapping("/interface")
@Api(value = "Interface", tags = "接口")
public class InterfaceController extends SuperController<InterfaceService, Long, Interface, InterfaceSaveVO,
    InterfaceUpdateVO, InterfacePageQuery, InterfaceResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


