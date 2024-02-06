package cn.lmx.kpu.test.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.test.entity.DefGenTestSimple;
import cn.lmx.kpu.test.service.DefGenTestSimpleService;
import cn.lmx.kpu.test.vo.query.DefGenTestSimplePageQuery;
import cn.lmx.kpu.test.vo.result.DefGenTestSimpleResultVO;
import cn.lmx.kpu.test.vo.save.DefGenTestSimpleSaveVO;
import cn.lmx.kpu.test.vo.update.DefGenTestSimpleUpdateVO;

/**
 * <p>
 * 前端控制器
 * 测试单表
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTestSimple")
@Tag(name = "测试单表")
public class DefGenTestSimpleController extends SuperController<DefGenTestSimpleService, Long, DefGenTestSimple, DefGenTestSimpleSaveVO,
        DefGenTestSimpleUpdateVO, DefGenTestSimplePageQuery, DefGenTestSimpleResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


