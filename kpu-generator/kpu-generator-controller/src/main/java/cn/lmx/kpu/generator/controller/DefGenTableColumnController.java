package cn.lmx.kpu.generator.controller;

import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.generator.entity.DefGenTableColumn;
import cn.lmx.kpu.generator.service.DefGenTableColumnService;
import cn.lmx.kpu.generator.vo.query.DefGenTableColumnPageQuery;
import cn.lmx.kpu.generator.vo.result.DefGenTableColumnResultVO;
import cn.lmx.kpu.generator.vo.save.DefGenTableColumnSaveVO;
import cn.lmx.kpu.generator.vo.update.DefGenTableColumnUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTableColumn")
@Tag(name = "代码生成字段")
public class DefGenTableColumnController extends SuperController<DefGenTableColumnService, Long, DefGenTableColumn, DefGenTableColumnSaveVO,
        DefGenTableColumnUpdateVO, DefGenTableColumnPageQuery, DefGenTableColumnResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Operation(summary = "同步字段结构", description = "同步字段结构")
    @PostMapping(value = "/syncField")
    @WebLog(value = "'同步字段结构")
    public R<Boolean> syncField(@RequestParam Long tableId, @RequestParam Long id) {
        return R.success(superService.syncField(tableId, id));
    }
}


