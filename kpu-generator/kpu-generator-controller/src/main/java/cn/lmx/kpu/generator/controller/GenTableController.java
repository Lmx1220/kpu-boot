package cn.lmx.kpu.generator.controller;

import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.service.GenTableService;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.save.GenVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "预览", notes = "预览")
    @PostMapping("previewCode")
    public R<Map<String, String>> previewCode(@RequestParam Long id, @RequestParam TemplateEnum templateEnum) {
        return R.success(superService.previewCode(id, templateEnum));
    }

    @ApiOperation(value = "生成", notes = "生成")
    @PostMapping("generatorCode")
    public R<Boolean> generatorCode(@RequestBody GenVO genVO) {
        superService.generatorCode(genVO);
        return R.success();
    }

    @ApiOperation(value = "查询生成表列表", notes = "查询生成表列表")
    @PostMapping("selectTableList")
    public R<List<GenTable>> selectTableList(@RequestBody PageParams<GenTablePageQuery> queryPageParams) {
        queryPageParams.getModel().getDsId();
        return R.success(superService.selectTableList());
    }

    @ApiOperation(value = "下载", notes = "下载")
    @PostMapping("downloadZip")
    public void downloadZip(HttpServletResponse response, @RequestParam List<Long> ids, @RequestParam TemplateEnum templateEnum) {
//        DownloaVo download = superService.downloadZip(ids,templateEnum);
//        write(download.getData(),download.getFileName,response);
    }

}