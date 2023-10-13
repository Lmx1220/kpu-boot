package cn.lmx.kpu.generator.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.service.GenTableColumnService;
import cn.lmx.kpu.generator.vo.query.GenTableColumnPageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableColumnResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableColumnSaveVO;
import cn.lmx.kpu.generator.vo.update.GenTableColumnUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/genTableColumn")
@Api(value = "GenTableColumn", tags = "代码生成字段")
public class GenTableColumnController extends SuperController<GenTableColumnService, Long, GenTableColumn, GenTableColumnSaveVO,
        GenTableColumnUpdateVO, GenTableColumnPageQuery, GenTableColumnResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @ApiOperation(value = "同步字段结构", notes = "同步字段结构")
    @PostMapping(value = "/syncField")
    @SysLog(value = "'同步字段结构")
    public R<Boolean> syncField(@RequestParam Long tableId, @RequestParam Long id) {
        return R.success(superService.syncField(tableId, id));
    }
}


