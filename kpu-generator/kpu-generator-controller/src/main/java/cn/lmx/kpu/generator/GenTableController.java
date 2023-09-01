package cn.lmx.kpu.generator;

import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.service.GenTableService;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:29
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/genTable")
@Api(value = "GenTable", tags = "代码生成")
public class GenTableController extends SuperController<GenTableService, Long, GenTable, GenTableSaveVO,
        GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}