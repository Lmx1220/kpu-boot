package cn.lmx.kpu.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.system.entity.system.DefClient;
import cn.lmx.kpu.system.service.system.DefClientService;
import cn.lmx.kpu.system.vo.query.system.DefClientPageQuery;
import cn.lmx.kpu.system.vo.result.system.DefClientResultVO;
import cn.lmx.kpu.system.vo.save.system.DefClientSaveVO;
import cn.lmx.kpu.system.vo.update.system.DefClientUpdateVO;


/**
 * <p>
 * 前端控制器
 * 客户端
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defClient")
@Tag(name = "客户端")
public class DefClientController extends SuperController<DefClientService, Long, DefClient, DefClientSaveVO, DefClientUpdateVO, DefClientPageQuery, DefClientResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}
