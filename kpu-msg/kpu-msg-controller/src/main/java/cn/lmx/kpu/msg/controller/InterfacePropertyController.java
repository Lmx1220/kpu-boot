package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.InterfacePropertyService;
import cn.lmx.kpu.msg.entity.InterfaceProperty;
import cn.lmx.kpu.msg.vo.save.InterfacePropertySaveVO;
import cn.lmx.kpu.msg.vo.update.InterfacePropertyUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfacePropertyResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePropertyPageQuery;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口属性
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
@RequestMapping("/interfaceProperty")
@Api(value = "InterfaceProperty", tags = "接口属性")
public class InterfacePropertyController extends SuperController<InterfacePropertyService, Long, InterfaceProperty, InterfacePropertySaveVO,
    InterfacePropertyUpdateVO, InterfacePropertyPageQuery, InterfacePropertyResultVO> {
    private final EchoService echoService;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


