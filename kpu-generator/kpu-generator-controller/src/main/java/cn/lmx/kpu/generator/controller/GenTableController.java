package cn.lmx.kpu.generator.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.DownloadController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.request.DownloadVO;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.service.GenTableService;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableImportVO;
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
public class GenTableController extends SuperController<GenTableService, Long, GenTable, GenTableSaveVO, GenTableUpdateVO, GenTablePageQuery, GenTableResultVO>
        implements DownloadController<Long, GenTable, GenTableSaveVO, GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @ApiOperation(value = "分页查询代码生成表", notes = "分页查询代码生成表")
    @PostMapping("/selectTableList")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<List<GenTable>> selectTableList(@RequestBody @Validated PageParams<GenTablePageQuery> params) {
        return R.success(superService.selectTableList(params.getModel().getDsId()));
    }

    @ApiOperation(value = "导入检测", notes = "导入检测")
    @PostMapping("/importCheck")
    @SysLog(value = "'导入检测")
    public R<Boolean> importCheck(@RequestBody @Validated List<String> tableNames) {
        return R.success(superService.importCheck(tableNames));
    }

    @ApiOperation(value = "导入表结构", notes = "导入表结构")
    @PostMapping(value = "/importTable")
    @SysLog(value = "'导入表结构", response = false)
    public R<Boolean> importTable(@RequestBody @Validated GenTableImportVO importVO) {
        return R.success(superService.importTable(importVO));
    }

    @ApiOperation(value = "同步表的字段", notes = "同步表的字段,新增或删除，不修改原来就存在的字段")
    @PostMapping("/syncField")
    @SysLog(value = "'同步表的字段")
    public R<Boolean> syncField(@RequestParam Long id) {
        superService.syncField(id);
        return R.success(true);
    }

    @ApiOperation(value = "批量查询", notes = "批量查询")
    @PostMapping("/findTableList")
    @SysLog(value = "'批量查询")
    public R<List<GenTableResultVO>> findTableList(@RequestBody List<Long> idList) {
        return R.success(superService.findTableList(idList));
    }

    @ApiOperation(value = "预览", notes = "预览")
    @PostMapping("/previewCode")
    @SysLog(value = "'预览")
    public R<Map<String, String>> previewCode(@RequestParam Long id, @RequestParam TemplateEnum template) {
        return R.success(superService.previewCode(id, template));
    }

    @ApiOperation(value = "批量生成代码", notes = "批量生成代码")
    @PostMapping("/generatorCode")
    @SysLog(value = "'批量生成代码")
    public R<Boolean> generatorCode(@RequestBody @Validated GenVO genVO) {
        superService.generatorCode(genVO);
        return R.success(true);
    }

    @ApiOperation(value = "批量下载代码", notes = "批量下载代码")
    @GetMapping(value = "/downloadZip", produces = "application/octet-stream")
    @SysLog(value = "'批量下载代码")
    public void downloadZip(HttpServletResponse response, @RequestParam List<Long> ids, @RequestParam TemplateEnum template) {
        DownloadVO download = superService.downloadZip(ids, template);
        write(download.getData(), download.getFileName(), response);
    }


    @Override
    public R<GenTableResultVO> getDetail(@RequestParam("id") Long id) {
        GenTableResultVO detail = superService.getDetail(id);
        echoService.action(detail);
        return R.success(detail);
    }

    @ApiOperation(value = "获取字段模板映射", notes = "获取字段模板映射")
    @GetMapping("/getFieldTemplate")
    @SysLog(value = "'获取字段模板映射")
    public R<Map<String, String>> getFieldTemplate() {
        return R.success(superService.getFieldTemplate());
    }

    @ApiOperation(value = "获取生成代码是否覆盖的默认配置", notes = "获取生成代码是否覆盖的默认配置")
    @GetMapping("/getFileOverrideStrategy")
    @SysLog(value = "'获取生成代码是否覆盖的默认配置")
    public R<Map<String, FileOverrideStrategyEnum>> getDefFileOverrideStrategy() {
        return R.success(superService.getFileOverrideStrategy());
    }


}