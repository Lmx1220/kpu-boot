package cn.lmx.kpu.file.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.DeleteController;
import cn.lmx.basic.base.controller.QueryController;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.service.FileService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 增量文件上传日志
 * </p>
 * FileDefController 和 FileBaseController的区别：
 * 1. FileDefController 操作def库的file表，上传和下载操作 都需要租户隔离时使用。 如：A租户下，某个审批单业务的附件。
 * 2. FileBaseController 操作base库的file表，上传和下载操作 可能或租户时使用。 如：user表头像、应用表头像（所有的租户都能需要能查看用户和应用头像）
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [初始创建]
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file/def")
@RequiredArgsConstructor
@Tag(name = "默认库-文件操作接口")
public class FileDefController extends SuperSimpleController<FileService, Long, File>
        implements QueryController<Long, File, File, File>, DeleteController<Long, File> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public Class<File> getResultVOClass() {
        return File.class;
    }

    @Override
    public void handlerQueryParams(PageParams<File> params) {
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> longs) {
        return R.successDef(true);
    }
}
