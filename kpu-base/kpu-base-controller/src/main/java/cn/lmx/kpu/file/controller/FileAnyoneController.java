package cn.lmx.kpu.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.file.service.FileService;
import cn.lmx.kpu.file.vo.param.FileUploadVO;
import cn.lmx.kpu.file.vo.result.FileResultVO;

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
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [初始创建]
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping({"/file/anyone", "/file/anyTenant"})
@Tag(name = "租户库-文件实时上传")
public class FileAnyoneController {
    private final FileService fileService;


    /**
     * 上传小文件到租户库
     * <p>
     * 适用于非租户请求的文件上传
     *
     * @param file         文件
     * @param fileUploadVO 附件信息
     */
    @Operation(summary = "上传文件", description = "上传小文件到租户库, 适用于租户请求的文件上传。 此接口上传的文件会存储到base库，调用此接口请确保请求头中有租户ID")
    @Parameters({
            @Parameter(name = "file", description = "附件", schema = @Schema(type = DATA_TYPE_MULTIPART_FILE), in = ParameterIn.QUERY, required = true),
    })
    @PostMapping(value = "/upload")
    @WebLog("上传小文件到租户库")
    public R<FileResultVO> upload(@RequestParam(value = "file") MultipartFile file,
                                  @Validated FileUploadVO fileUploadVO) {
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return R.validFail(BASE_VALID_PARAM.build("请上传有效文件"));
        }
        return R.success(fileService.upload(file, fileUploadVO));
    }

    /**
     * 根据文件id，获取访问路径
     *
     * @param ids 文件id
     */
    @Operation(summary = "根据文件id查询文件的临时访问路径", description = "根据文件id查询文件的临时访问路径")
    @PostMapping(value = "/findUrlById")
    @WebLog("根据文件id，获取文件临时的访问路径")
    public R<Map<Long, String>> findUrlFormTenantById(@RequestBody List<Long> ids) {
        return R.success(fileService.findUrlById(ids));
    }

    /**
     * 在租户库库下载一个文件或多个文件打包下载
     *
     * @param ids 文件id
     */
    @Operation(summary = "根据文件id打包下载文件", description = "根据文件id打包下载文件")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @WebLog("下载附件")
    public void downloadFromTenant(@RequestParam List<Long> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArgumentAssert.notEmpty(ids, "请选择至少一个附件");
        fileService.download(request, response, ids);
    }
}
