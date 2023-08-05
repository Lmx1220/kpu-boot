package cn.lmx.kpu.file.controller;

import cn.lmx.basic.interfaces.echo.EchoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.DeleteController;
import cn.lmx.basic.base.controller.QueryController;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.service.FileService;
import cn.lmx.kpu.file.vo.param.FileParamVO;
import cn.lmx.kpu.file.vo.param.FileUploadVO;
import cn.lmx.kpu.file.vo.result.FileResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.lmx.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static cn.lmx.kpu.common.constant.SwaggerConstants.DATA_TYPE_MULTIPART_FILE;

/**
 * <p>
 * 前端控制器
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27] [lmx] [初始创建]
 */
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/file")
@Api(value = "FileFileController", tags = "文件实时上传")
public class FileController extends SuperSimpleController<FileService, Long, File, File, File, FileParamVO, FileResultVO>
        implements QueryController<Long, File, File, File, FileParamVO, FileResultVO>, DeleteController<Long, File, File, File, FileParamVO, FileResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 上传文件
     */
    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", dataType = DATA_TYPE_MULTIPART_FILE, allowMultiple = true, required = true),
    })
    @PostMapping(value = "/anyone/upload")
    @SysLog("上传附件")
    public R<FileResultVO> upload(@RequestParam(value = "file") MultipartFile file,
                                  @Validated FileUploadVO attachmentVO) {
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return R.validFail(BASE_VALID_PARAM.build("请上传有效文件"));
        }
        return R.success(superService.upload(file, attachmentVO));
    }


    /**
     * 根据文件相对路径，获取访问路径
     *
     * @param paths 文件路径
     */
    @ApiOperation(value = "批量根据文件相对路径，获取文件临时的访问路径", notes = "批量根据文件相对路径，获取文件临时的访问路径")
    @PostMapping(value = "/anyone/findUrlByPath")
    @SysLog("批量根据文件相对路径，获取文件临时的访问路径")
    public R<Map<String, String>> findUrlByPath(@RequestBody List<String> paths) {
        return R.success(superService.findUrlByPath(paths));
    }

    /**
     * 根据文件id，获取访问路径
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id，获取文件临时的访问路径", notes = "根据文件id，获取文件临时的访问路径")
    @PostMapping(value = "/anyone/findUrlById")
    @SysLog("根据文件id，获取文件临时的访问路径")
    public R<Map<Long, String>> findUrlById(@RequestBody List<Long> ids) {
        return R.success(superService.findUrlById(ids));
    }

    /**
     * 下载一个文件或多个文件打包下载
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id打包下载", notes = "根据附件id下载多个打包的附件")
    @PostMapping(value = "/download", produces = "application/octet-stream")
    @SysLog("下载附件")
    public void download(@RequestBody List<Long> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArgumentAssert.notEmpty(ids, "请选择至少一个附件");
        superService.download(request, response, ids);
    }

}
