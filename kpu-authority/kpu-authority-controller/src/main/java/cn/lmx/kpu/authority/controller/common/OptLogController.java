package cn.lmx.kpu.authority.controller.common;


import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.DeleteController;
import cn.lmx.basic.base.controller.PoiController;
import cn.lmx.basic.base.controller.QueryController;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.authority.dto.common.OptLogResult;
import cn.lmx.kpu.authority.entity.common.OptLog;
import cn.lmx.kpu.authority.service.common.OptLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "系统日志")
@PreAuth(replace = "authority:optLog:")
public class OptLogController extends SuperSimpleController<OptLogService, Long, OptLog, OptLog, OptLog, OptLog, OptLogResult>
        implements DeleteController<Long, OptLog, OptLog, OptLog, OptLog, OptLogResult>,
        QueryController<Long, OptLog, OptLog, OptLog, OptLog, OptLogResult>,
        PoiController<Long, OptLog, OptLog, OptLog, OptLog, OptLogResult> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public R<OptLogResult> getDetail(@RequestParam("id") Long id) {
        return success(superService.getOptLogResultById(id));
    }

    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }
        return success(superService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
